package zad2;

import java.awt.event.*;

@FunctionalInterface
public interface MousePressListener extends MouseListener {
	
	@Override
	abstract void mousePressed(MouseEvent e);
	@Override
	default void mouseReleased(MouseEvent e) {}
	@Override
	default void mouseEntered(MouseEvent e) {}
	@Override
	default void mouseExited(MouseEvent e) {}
	@Override
	default void mouseClicked(MouseEvent e) {}
	
}
