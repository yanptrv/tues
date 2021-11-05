import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import operations.Operation;
import operations.OperationFactory;

@WebServlet(name = "MyServlet", urlPatterns = "/echo/*")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("msg");
//        String pathInfo = req.getPathInfo(); // /{value}/test
//        String[] pathParts = pathInfo.split("/");

        OperationFactory operationFactory = new OperationFactory();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(msg);

        try {
            Operation operation = operationFactory.getOperation(msg);
            resp.setStatus(200);
            resp.getWriter().println(operation.perform());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}