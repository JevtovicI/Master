package jwd.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Activity;
import jwd.apoteka.web.dto.ActivityDTO;

@Component
public class ActivityToActivityDTO 
	implements Converter<Activity, ActivityDTO> {

	@Override
	public ActivityDTO convert(Activity activity) {
		if(activity==null){
			return null;
		}
		
		ActivityDTO dto = new ActivityDTO();
		
		dto.setId(activity.getId());
		dto.setName(activity.getName());
		
		return dto;
	}
	
	public List<ActivityDTO> convert(List<Activity> activities){
		List<ActivityDTO> ret = new ArrayList<>();
		
		for(Activity a: activities){
			ret.add(convert(a));
		}
		
		return ret;
	}

}
