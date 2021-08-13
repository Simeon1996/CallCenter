package base;

import java.util.*;

public class CallCenter {
    private final int LEVELS = 3;

    private final int NUM_RESPONDENTS = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;

    /**
     * List of employees, by level.
     * - employeeLevels[0] = respondents
     * - employeeLevels[1] = managers
     * - employeeLevels[2] = directors
     */
    List<List<? extends Employee>> employeeLevels;
    List<List<Call>> callQueues;

    public CallCenter() {
        employeeLevels = new ArrayList<>(LEVELS);
        callQueues = new ArrayList<>(LEVELS);

        List<Employee> respondents = new ArrayList<>(NUM_RESPONDENTS);
        for (int i = 0; i < NUM_RESPONDENTS; i++) {
            respondents.add(new Respondent(this));
        }
        employeeLevels.add(respondents);

        List<Employee> managers = new ArrayList<>(NUM_MANAGERS);
        managers.add(new Manager(this));
        employeeLevels.add(managers);

        List<Employee> directors = new ArrayList<>(NUM_DIRECTORS);
        managers.add(new Director(this));
        employeeLevels.add(directors);
    }

    public Employee getHandlerForCall(Call call) {
        for (int level = call.getRank().getValue(); level < LEVELS - 1; level++) {
            List<? extends Employee> employeeLevel = employeeLevels.get(level);
            for (Employee employee : employeeLevel) {
                if (employee.isFree()) {
                    return employee;
                }
            }
        }

        return null;
    }

    public void dispatchCall(Call call) {
        Employee emp = getHandlerForCall(call);

        if (emp != null) {
            emp.handleCall(call);
            call.setHandler(emp);
        } else {
            call.reply("Please wait for an employee to reply.");
            callQueues.get(call.getRank().getValue()).add(call);
        }
    }

    public boolean assignCall(Employee employee) {
        for (int rank = employee.getRank().getValue(); rank >= 0; rank--) {
            List<Call> queue = callQueues.get(rank);

            if (queue.size() > 0) {
                Call call = queue.remove(0);
                if (call != null) {
                    employee.handleCall(call);
                    return true;
                }
            }
        }

        return false;
    }
}
