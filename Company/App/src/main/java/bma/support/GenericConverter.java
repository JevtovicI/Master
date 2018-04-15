package bma.support;

import java.util.List;

public interface GenericConverter<T1, T2> {

	T1 modelToDto(T2 t2);

	T2 dtoToModel(T1 t1);
	
	List<T1> modelListToDto(List<T2> t2s);

}
