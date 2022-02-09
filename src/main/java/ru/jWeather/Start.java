package ru.jWeather;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Start {
    public static void main(String[] args) throws Exception {

        JDA builder = JDABuilder.createDefault("ODg5MTAxNTAwMDY2OTgzOTM4.YUcWTA.ZkdBQjHaJyYZTthNqZ2NMq__Hq0")
                .addEventListeners(new MessageHandler())
                .setActivity(Activity.playing("крутая игра на Java!"))
                .build();
    }
}
