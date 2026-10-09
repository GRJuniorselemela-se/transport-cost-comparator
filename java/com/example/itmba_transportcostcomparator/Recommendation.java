package com.example.itmba_transportcostcomparator;

public class Recommendation {

    public String getCostCategory(double monthlyCost) {
        if (monthlyCost < 1000) {
            return "Low Cost";
        }
        else if (monthlyCost >= 1000 && monthlyCost < 2000) {
            return "Moderate";
        }
        else if (monthlyCost >= 2000 && monthlyCost < 3000) {
            return "High";
        }
        else if (monthlyCost >= 3000 && monthlyCost < 5000) {
            return "Very High";
        }
        else {
            return "Excessive";
        }
    }

    public String getRecommendation(String category) {
        switch (category) {
            case "Low Cost":
                return "Continue using your current transport method.";
            case "Moderate":
                return "Consider carpooling where possible.";
            case "High":
                return "Reduce unnecessary trips and combine errands.";
            case "Very High":
                return "Consider public transport for regular commuting.";
            case "Excessive":
                return "Immediate action is recommended to reduce transport costs.";
            default:
                return "Please calculate your transport costs first.";
        }
    }

    public RecommendationResult getCategoryAndRecommendation(double monthlyCost) {
        String category = getCostCategory(monthlyCost);
        String recommendation = getRecommendation(category);
        return new RecommendationResult(category, recommendation);
    }

    public static class RecommendationResult {
        public final String category;
        public final String recommendation;

        public RecommendationResult(String category, String recommendation) {
            this.category = category;
            this.recommendation = recommendation;
        }
    }
}
