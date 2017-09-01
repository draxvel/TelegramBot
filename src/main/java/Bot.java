import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Random;

public class Bot extends TelegramLongPollingBot {

    private static final String NAME_OF_BOT = "Draxvel";
    private static final String BOT_TOKEN = "440095628:AAHaANCyYs8Ho1znKoEWS5g5ciRh1leaevQ";

    private String [] answers = {
                "Безперечно",
                "Так",
                "Ні",
                "Жодних сумнівів",
                "Безумовно, що так",
                "Можеш бути впевнений в цьому",
                "Мені здається, що так",
                "Ймовірніше за все",
                "Хороші перспективи",
                "Знаки кажуть - «так»",
                "Поки не ясно, спробуй знову",
                "Запитай пізніше",
                "Краще забудь про це",
                "Зараз не можна передбачити",
                "Сконцентруйся і запитай знову",
                "Навіть не думай",
                "Моя відповідь ні",
                "За моїми даними - «ні»",
                "Перспективи не дуже хороші",
                "Дуже сумнівно"};

    UserMarkup userMarkup = new UserMarkup();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if(message !=null && message.hasText())
        {
            if(message.getText().toString().equals("hi") || message.getText().toString().equals("/start"))
                 sendMsg(message, "Привіт, " + message.getFrom().getFirstName()+ ". Спитай мене щось.", userMarkup);

            else if(message.getText().toString().equals("bye"))
                 sendMsg(message, "Папа, " + message.getFrom().getFirstName() + ". Я сумуватиму :(", userMarkup);
            else {
                if (!message.getText().substring(message.getText().length() - 1).equals("?"))
                {
                    sendMsg(message, "Запитання задано не коректно. Спробуй ще раз.",userMarkup );
                }
                else
                {
                    Random randomGenerator = new Random();
                    int index = randomGenerator.nextInt(20);
                    sendMsg(message, answers[index], userMarkup);
                 }
            }
        }
    }

    private void sendMsg(Message message, String s, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage sMsg = new SendMessage();
        sMsg.setChatId(message.getChatId().toString());
        sMsg.setReplyMarkup(replyKeyboardMarkup);
        sMsg.setText(s);
        try {
            sendMessage(sMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return NAME_OF_BOT;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
