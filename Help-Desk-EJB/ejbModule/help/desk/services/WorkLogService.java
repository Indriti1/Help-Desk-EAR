package help.desk.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import help.desk.entities.Project;
import help.desk.entities.User;
import help.desk.entities.WorkLog;

public interface WorkLogService {
    void logWork(WorkLog workLog);
    List<WorkLog> getWorkLogsByEmployee(Long employeeId);
    List<WorkLog> getWorkLogsByProject(Long projectId);
    List<WorkLog> getWorkLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    Map<User, Integer> generateEmployeeWorkHoursReport(LocalDateTime startDate, LocalDateTime endDate);
    Map<Project, Integer> generateProjectWorkHoursReport(LocalDateTime startDate, LocalDateTime endDate);
	void logWork(Long id, Long selectedTaskId, Integer hoursWorked);
}