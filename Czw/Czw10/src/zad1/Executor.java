package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Executor extends JFrame implements ActionListener {
    private JTextArea jTextArea;
    private JButton stop;
    private ArrayList<ThreadButton> jButtonArrayList;
    private ExecutorService executorService;

    Executor(int threadsCount) {
        executorService = Executors.newFixedThreadPool(threadsCount);
        jButtonArrayList = new ArrayList<>(threadsCount);
        jTextArea = new JTextArea(50, 40);
        add(new JScrollPane(jTextArea));

        JPanel panel = new JPanel();
        JPanel panelTop = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.stop = new JButton("STOP");
        this.stop.setActionCommand("Shutdown");
        this.stop.addActionListener(this);
        this.stop.setPreferredSize(new Dimension(600, 30));
        panelTop.add(this.stop);

        for (int i = 0; i < threadsCount; i++) jButtonArrayList.add(new ThreadButton(i + 1));
        for (ThreadButton jb : jButtonArrayList) {

            jb.setActionCommand("Start");
            jb.addActionListener(this);
            panel.add(jb);
        }

        add(panel, "South");
        add(panelTop, "North");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        ActionListener changeALL = e -> {
            if (stop.getText().equals("STOP")) {
                stop.setEnabled(false);
                for (JButton jb : jButtonArrayList)
                    jb.setEnabled(false);
            }
        };
        stop.addActionListener(changeALL);
    }

    public void actionPerformed(ActionEvent e) {
        for (ThreadButton jb : jButtonArrayList) {
            if ((e.getSource()).equals(jb))
                if (jb.isPressed && jb.runner != null) {
                    jb.runner.cancel(true);
                    jb.setText("Cancel");
                    jb.setEnabled(false);
                    jTextArea.append("Thread" + jb.sumTask.taskNum + ": Cancelled!" + '\n');
                    jb.isPressed = false;
                } else if (e.getActionCommand().equals("Suspend"))
                    taskSuspend(jb.sumTask);
                else if (e.getActionCommand().equals("Continue"))
                    taskContinue(jb.sumTask);
                else jb.taskStart();
        }

        if ((e.getSource()).equals(stop))
            if (e.getActionCommand().equals("Shutdown"))
                taskShutdown();
    }

    private void DoneAll() {
        for (ThreadButton jb : jButtonArrayList)
            if ((jb.getText().equals("Suspend") || jb.getText().equals("Continue")))
                if (jb.sumTask.sum >= jb.sumTask.limit) {
                    jb.setEnabled(false);
                    jb.setText("DONE");
                    jTextArea.append("Thread " + jb.sumTask.taskNum + ": Done!" + '\n');
                }
    }

    private void taskContinue(SumTask sumTask) {
        sumTask.wakeUp();
    }

    private void taskSuspend(SumTask sumTask) {
        sumTask.running = false;
    }

    private void taskShutdown() {
        for (ThreadButton jb : jButtonArrayList)
            if (jb.runner != null && jb.runner.isDone())
                jb.setText("DONE");
            else if (jb.runner != null) {
                jb.runner.cancel(true);
                if (jb.getText().equals("Suspend") || jb.getText().equals("Continue")) {
                    jb.setText("DONE");
                    jTextArea.append("Thread" + jb.number + ": Done!" + '\n');
                }
            }
    }

    class SumTask implements Callable<Integer> {
        volatile boolean running = true;
        private int taskNum, limit, sum, wrt;

        SumTask(int taskNum, int limit) {
            this.taskNum = taskNum;
            this.limit = limit;
        }

        public synchronized Integer call() throws Exception {
            while (sum <= limit) {
                while (!running)
                    wait();
                wrt = (int) (Math.random() * 100);
                sum += wrt;
                if (Thread.currentThread().isInterrupted()) return null;
                jTextArea.append("Thread " + taskNum + "(limit = " + limit + "):" + wrt + " sum = " + sum + '\n');
                Thread.sleep(1000);
            }
            DoneAll();
            return sum;
        }

        synchronized void wakeUp() {
            running = true;
            notifyAll();
        }
    }

    class ThreadButton extends JButton {
        SumTask sumTask;
        Future<Integer> runner;
        private String name;
        private int number;
        boolean isPressed = false;

        ThreadButton(int i) {
            super("Thread " + i);
            this.number = i;
            this.name = "Thread " + i;
            addKeyListener(new MyNewSuperKeyAdapter(this));
            ActionListener change = e -> {
                if (this.getText().equals(name)) {
                    this.setText("Suspend");
                    this.setActionCommand("Suspend");
                } else if (this.getText().equals("Suspend")) {
                    this.setText("Continue");
                    this.setActionCommand("Continue");
                } else if (this.getText().equals("Continue")) {
                    this.setText("Suspend");
                    this.setActionCommand("Suspend");
                }
            };
            addActionListener(change);
        }

        void taskStart() {
            try {
                this.sumTask = new SumTask(this.number, 300);
                runner = executorService.submit(this.sumTask);
            } catch (RejectedExecutionException exc) {
                exc.printStackTrace();
            }
        }
    }

    class MyNewSuperKeyAdapter extends KeyAdapter {
        ThreadButton threadButton;

        MyNewSuperKeyAdapter(ThreadButton threadButton){
            super();
            this.threadButton = threadButton;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 67)
                if (e.getSource().equals(threadButton)) threadButton.isPressed = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource().equals(threadButton)) threadButton.isPressed = false;
        }
    }
}