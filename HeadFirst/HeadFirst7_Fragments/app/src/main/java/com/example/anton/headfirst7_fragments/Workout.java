package com.example.anton.headfirst7_fragments;

public class Workout {
    private String name;
    private String decryption;

    private static final Workout[] workouts={
            new Workout("The Limb Loosener",
                    "5 Handstand push-ups\n10 1-legged squats\n15 Pull-ups"),
            new Workout("Core Agony",
                    "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
            new Workout("The Wimp Special",
                    "5 Pull-ups\n10 Push-ups\n15 Squats"),
            new Workout("Strength and Length",
                    "500 meter run\n21 x 1.5 pood kettleball swing\n21 x pull-ups")
    };

   private Workout(String name, String decryption){
        this.name=name;
        this.decryption =decryption;
   }

    public String getName() {
        return name;
    }

    public String getDecryption() {
        return decryption;
    }

    public static Workout[] getWorkouts() {
        return workouts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDecryption(String decryption) {
        this.decryption = decryption;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
