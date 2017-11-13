package zad4;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;

class InputConverter<T> {
	private T value;
	
	InputConverter(T value){
		this.value = value;
	}

	<T> T convertBy(Function... arg) {
		Object object = value;

		try {
			for (Function function : arg)
				object = applyTo(getInstance(object), function);
			return (T) getInstance(object);
		}catch (Exception e){
			return (T) object;
		}
	}

	private Object getInstance(Object obj) throws Exception {
		Class type = obj.getClass();
		Constructor constructor = null;

		try {
			constructor = type.getConstructor(type);
		} catch (Exception exc) {
			if (obj instanceof java.util.Collection)
				constructor = ArrayList.class.getConstructor(java.util.Collection.class);

			if (obj instanceof Number)
				constructor = Number.class.getConstructor(String.class);
		}

		if (constructor == null) throw new UnsupportedOperationException(type.toString());
		else return constructor.newInstance(obj);
	}

	private Object applyTo(Object obj, Function function) throws Exception {

		if (obj instanceof String) {
			ArrayList arrayList = (ArrayList) function.apply(obj);

			try { return arrayList.getClass().getConstructor(java.util.Collection.class).newInstance(arrayList); }
			catch (Exception ex) { ex.printStackTrace(); }
		}

		if (obj instanceof java.util.Collection) {
			String string = function.apply(obj).toString();

			try {
				Integer num = Integer.parseInt(string);
				return num.getClass().getConstructor(java.lang.String.class).newInstance(string);
			} catch (NumberFormatException e) {
				try { return string.getClass().getConstructor(java.lang.String.class).newInstance(string); }
				catch (Exception ex) { ex.printStackTrace(); }
			}
		}

		return obj.getClass().getDeclaredMethod("", obj.getClass()).invoke(obj, function);
	}
}
