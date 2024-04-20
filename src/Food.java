public class Food {
    FoodChoice title;

    public Food(FoodChoice title, int weight, Nutrition nutrition) {
        this.title = title;
        this.weight = weight;
        this.nutrition = nutrition;
    }

    int weight;
    float cost;
    Nutrition nutrition;


    public float[] PFC(){
        float[] pfc = new float[3];
        pfc[0] = nutrition.protein() * weight;
        pfc[1] =  nutrition.fat() * weight;
        pfc[2] = nutrition.carbs() * weight;
        return pfc;
    }

    public float totalCost(){
        return cost * weight/100;
    }

    public record Nutrition(float protein, float fat, float carbs){
        public Nutrition() {
            this(0.0f, 0.0f, 0.0f); // Default values for protein, fat, and carbs
        }
    };
}

