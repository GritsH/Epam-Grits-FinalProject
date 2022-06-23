package by.grits.news.controller;

import by.grits.news.command.Command;
import by.grits.news.command.RequestParameter;
import by.grits.news.command.Router;
import by.grits.news.command.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
@MultipartConfig
public class Controller extends HttpServlet {
    static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() {
        LOGGER.info("Servlet init: " + this.getServletInfo());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.of(commandName);
        LOGGER.info("Command name = " + commandName);
        try {
            Router router = command.execute(request);
            String toPage = router.getPage();

            switch (router.getType()) {
                case FORWARD:
                    request.getRequestDispatcher(toPage).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(toPage);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (CommandException e) {
            LOGGER.error("Error while command execution " + commandName, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, "Servlet destroyed: " + this.getServletName());
    }
}
