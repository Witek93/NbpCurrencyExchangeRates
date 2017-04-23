package model;

import static model.StatisticsResult.Status.FAILURE;
import static model.StatisticsResult.Status.SUCCESS;

public class StatisticResults {
    public static StatisticsResult success() {
        return new StatisticsResult(SUCCESS, "Successfully responded");
    }
    public static StatisticsResult failure(Exception e) {
        return new StatisticsResult(FAILURE, "Unhandled exception: " + e.getMessage());
    }
    public static StatisticsResult ratesServiceFailure(RatesResult result) {
        return new StatisticsResult(FAILURE, "Failure from RatesService: " + result.name());
    }

}
