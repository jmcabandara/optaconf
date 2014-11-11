package org.optaconf.service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.optaconf.bridge.devoxx.DevoxxImporter;
import org.optaconf.cdi.ScheduleManager;
import org.optaconf.domain.Schedule;
import org.optaconf.domain.Talk;
import org.optaconf.domain.TalkExclusion;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

@Path("/{conferenceId}/schedule")
public class ScheduleService {

    @Inject
    private ScheduleManager scheduleManager;

    @Inject
    private DevoxxImporter devoxxImporter;

    @Inject
    private SolverFactory solverFactory;

    @GET // TODO should be post
    @Path("/import/devoxx")
    @Produces("application/json")
    public String importDevoxx(@PathParam("conferenceId") Long conferenceId) {
        Schedule schedule = devoxxImporter.importSchedule();
        scheduleManager.setSchedule(schedule);
        return "Devoxx schedule with " + schedule.getDayList().size() + " days, "
                + schedule.getTimeslotList().size() + " timeslots, "
                + schedule.getRoomList().size() + " rooms, "
                + schedule.getTalkList().size() + " talks imported successfully.";
    }

    @GET // TODO should be post
    @Path("/solve")
    @Produces("application/json")
    public String solveSchedule(@PathParam("conferenceId") Long conferenceId) {
        Solver solver = solverFactory.buildSolver();
        solver.solve(scheduleManager.getSchedule());
        scheduleManager.setSchedule((Schedule) solver.getBestSolution());
        return "Solved successfully. Sorry it took 20 seconds to respond."; // TODO go async
    }

}
