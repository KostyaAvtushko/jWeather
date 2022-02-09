package ru.jWeather;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MessageHandler extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        WeatherController weatherController = new WeatherController();
        try {
            event.getMember().getJDA().awaitReady().getCategories().get(0).getTextChannels().get(0).sendMessage(weatherController.weatherRequest(event.getMessage().getContentDisplay()))
                    .timeout(100, TimeUnit.SECONDS).submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
