package demo.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.bean.JobInputBean;
import demo.entity.Job;
import demo.service.JobService;

@RestController
@RequestMapping("demo/jobs")
public class JobsController {

	@Autowired
	JobService jobService;
	
	@RequestMapping(value = "/{id}/{year}/{month}", method = RequestMethod.GET)
	public List<Job> getJobsPerMonth(@PathVariable("id") int id,
			@PathVariable("year") String year,@PathVariable("month") String month) {
		
		List<Job> list = jobService.getJobsPerMonth(id, year, month);;
		return list;
	}
	
	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	public List<Job> getIdsJobsAll(@PathVariable("id") int id){
		List<Job> list = jobService.findById(id);
		return list;
	}
	
	@RequestMapping(value = "/{id}/{year:^[0-9]{4}$}/{month:^[0-9]{2}$}/{day:^[0-9]{2}$}", method = RequestMethod.POST)
	public Job createJob(@PathVariable("id") int id,
			@PathVariable("year") String year,@PathVariable("month") String month,
			@PathVariable("day") String day,@Validated @RequestBody JobInputBean input) {
		
		Job insertJob = jobService.createJob(id,year,month,day,input);
		return insertJob;
		
	}
	
}