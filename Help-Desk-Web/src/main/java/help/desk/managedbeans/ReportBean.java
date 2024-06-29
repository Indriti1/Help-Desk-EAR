package help.desk.managedbeans;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletResponse;
import help.desk.services.WorkLogService;
import help.desk.entities.User;
import help.desk.entities.Project;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReportBean {

    @EJB
    private WorkLogService workLogService;

    public void generateEmployeeWorkHoursReport() {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(1); // Last month
        LocalDateTime endDate = LocalDateTime.now();
        Map<User, Integer> employeeHours = workLogService.generateEmployeeWorkHoursReport(startDate, endDate)
        		.entrySet()
        		.stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(User::getUsername)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        generateCSVReport(employeeHours, "Employee_Work_Hours_Report.csv", "Employee,Total Hours");
    }

    public void generateProjectWorkHoursReport() {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(1); // Last month
        LocalDateTime endDate = LocalDateTime.now();
        Map<Project, Integer> projectHours = workLogService.generateProjectWorkHoursReport(startDate, endDate)
        		.entrySet()
        		.stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Project::getName)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
        generateCSVReport(projectHours, "Project_Work_Hours_Report.csv", "Project,Total Hours");
    }

    private <T> void generateCSVReport(Map<T, Integer> data, String fileName, String header) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        try (OutputStream output = response.getOutputStream()) {
            output.write((header + "\n").getBytes());
            for (Map.Entry<T, Integer> entry : data.entrySet()) {
                String line = entry.getKey().toString() + "," + entry.getValue() + "\n";
                output.write(line.getBytes());
            }
            output.flush();
            facesContext.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
