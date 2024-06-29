package help.desk.rest;

import java.util.List;
import java.util.stream.Collectors;

import help.desk.dto.TaskDTO;
import help.desk.entities.Task;
import help.desk.entities.User;
import help.desk.services.TaskService;
import help.desk.services.UserService;
import help.desk.utils.UserRole;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tasks")
@Produces("text/plain")
@Consumes(MediaType.APPLICATION_JSON)
public class TaskRessource {

    @EJB
    private TaskService taskService;

    @EJB
    private UserService userService;

    @GET
    @Path("/customer/{username}")
    public Response getCustomerTasks(@PathParam("username") String username, 
                                     @QueryParam("password") String password) {
        User user = userService.login(username, password);
        if (user == null || user.getRole() != UserRole.CUSTOMER) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        List<Task> tasks = taskService.getTasksByUserId(user.getId());
        List<TaskDTO> taskDTOs = tasks.stream()
                                      .map(this::convertToDTO)
                                      .collect(Collectors.toList());

        return Response.ok(taskDTOs).build();
    }

    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
            task.getId(),
            task.getTitle(),
            task.getStatus(),
            taskService.getTotalHoursWorked(task.getId())
        );
    }
}