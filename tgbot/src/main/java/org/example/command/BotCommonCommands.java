package org.example.command;

public class BotCommonCommands {

    @AppBotCommand(name = "/start", description = "when user tap in /start",
            showInHelp = true)
    String starting(){
        return "Бот запустился :)";
    }


    @AppBotCommand(name = "/hello", description = "when request hello",
            showInHelp = true)
    String hello(){
        return "Привет, дружище! Давно не виделись :)";
    }


    String noCap(){
        return "Вы ввели некорректный запрос!";
    }
    @AppBotCommand(name = "/bye", description = "when request bye",
            showInHelp = true)
    String bye(){
        return "Удачи.";
    }
    @AppBotCommand(name = "/help", description = "when request help",
            showInKeyboard = true)
    String help(){
        return "Команда /help в данный момент не сможет оказать тебе помощи)). В дальнейшем в данном разделе будут все доступные команды бота";

    }
}