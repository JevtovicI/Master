package jwd.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Activity;
import jwd.apoteka.service.ActivityService;
import jwd.apoteka.web.dto.ActivityDTO;
@Component
public class ActivityDTOToActivity 
	implements Converter<ActivityDTO, Activity> {
	
	@Autowired
	ActivityService activityService;

	@Override
	public Activity convert(ActivityDTO dto) {
		Activity activity = new Activity();
		
		if(dto.getId()!=null){
			activity = activityService.findOne(dto.getId());
			
			if(activity == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant activity");
			}
		}
		
		activity.setId(dto.getId());
		activity.setName(dto.getName());
		
		return activity;
	}
	
	public List<Activity> convert (List<ActivityDTO> dtoActivities){
		List<Activity> activities = new ArrayList<>();
		
		for(ActivityDTO dto : dtoActivities){
			activities.add(convert(dto));
		}
		
		return activities;
	}

}
