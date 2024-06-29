package help.desk.servicesImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import help.desk.dao.ProjectDAO;
import help.desk.dao.UserDAO;
import help.desk.dao.WorkLogDAO;
import help.desk.dao.TaskDAO;
import help.desk.entities.Project;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.entities.WorkLog;
import help.desk.services.WorkLogService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class WorkLogServiceImpl implements WorkLogService {
	
    @EJB
    private WorkLogDAO workLogDAO;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private ProjectDAO projectDAO;
    
    @EJB
    private TaskDAO taskDAO;

    @Override
    public void logWork(WorkLog workLog) {
        workLogDAO.addWorkLog(workLog);
    }

    @Override
    public List<WorkLog> getWorkLogsByEmployee(Long employeeId) {
        User employee = userDAO.findByUserId(employeeId);
        return workLogDAO.findWorkLogsByEmployee(employee);
    }

    @Override
    public List<WorkLog> getWorkLogsByProject(Long projectId) {
        Project project = projectDAO.findProjectById(projectId);
        List<WorkLog> projectWorkLogs = new ArrayList<>();
        for (Task task : project.getTasks()) {
            projectWorkLogs.addAll(workLogDAO.findWorkLogsByTask(task));
        }
        return projectWorkLogs;
    }

    @Override
    public List<WorkLog> getWorkLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return workLogDAO.findWorkLogsByDateRange(startDate, endDate);
    }

    @Override
    public Map<User, Integer> generateEmployeeWorkHoursReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<WorkLog> workLogs = workLogDAO.findWorkLogsByDateRange(startDate, endDate);
        Map<User, Integer> employeeHours = new HashMap<>();
        for (WorkLog workLog : workLogs) {
            User employee = workLog.getEmployee();
            employeeHours.put(employee, employeeHours.getOrDefault(employee, 0) + workLog.getHours());
        }
        return employeeHours;
    }

    @Override
    public Map<Project, Integer> generateProjectWorkHoursReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<WorkLog> workLogs = workLogDAO.findWorkLogsByDateRange(startDate, endDate);
        Map<Project, Integer> projectHours = new HashMap<>();
        for (WorkLog workLog : workLogs) {
            Project project = workLog.getTask().getProject();
            projectHours.put(project, projectHours.getOrDefault(project, 0) + workLog.getHours());
        }
        return projectHours;
    }

    @Override
    public void logWork(Long employeeId, Long selectedTaskId, Integer hoursWorked) {
        User employee = userDAO.findByUserId(employeeId);
        Task task = taskDAO.findTaskById(selectedTaskId);
        if (employee != null && task != null) {
            WorkLog workLog = new WorkLog();
            workLog.setEmployee(employee);
            workLog.setTask(task);
            workLog.setHours(hoursWorked);
            workLog.setDate(LocalDateTime.now());
            workLog.setComment("Work logged through employee dashboard");
            workLogDAO.addWorkLog(workLog);
        } else {
            throw new IllegalArgumentException("Invalid employee ID or task ID");
        }
    }
}