package help.desk.dao;

import java.time.LocalDateTime;
import java.util.List;

import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.entities.WorkLog;

public interface WorkLogDAO {
	void addWorkLog(WorkLog workLog);
    void editWorkLog(WorkLog workLog);
    void deleteWorkLog(WorkLog workLog);
    WorkLog findByWorkLogId(Long workLogId);
    List<WorkLog> findWorkLogsByEmployee(User employee);
    List<WorkLog> findWorkLogsByTask(Task task);
    List<WorkLog> findWorkLogsByDateRange(LocalDateTime startDate, LocalDateTime endDates);
	int getTotalHoursForTask(Long taskId);
}