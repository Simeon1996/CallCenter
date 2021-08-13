package base;

public abstract class Employee {
    private Call currentCall = null;
    protected Rank rank;
    private CallCenter callCenter;

    public Employee(CallCenter center) {
        callCenter = center;
    }

    public void handleCall(Call call) {
        currentCall = call;
    }

    public void callFinished() {
        if (currentCall != null) {
            currentCall.finish();

            currentCall = null;
        }

        assignNewCall();
    }

    public void escalateAndAssign() {
        if (currentCall != null) {
            currentCall.incrementRank();
            callCenter.dispatchCall(currentCall);

            currentCall = null;
        }

        assignNewCall();
    }

    public boolean assignNewCall() {
        if (!isFree()) {
            return false;
        }

        return callCenter.assignCall(this);
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isFree() {
        return currentCall == null;
    }
}
