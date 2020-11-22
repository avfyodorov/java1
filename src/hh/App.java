package hh;

public class App{
    public static void main(String[] args) {
String s="qyqy abra";
if(s.toLowerCase().contains("ab"))
        System.out.println("AAAA");
    }
}
/*
//        java1-vk-bot/src/main/java/Vk/App.java /
//        @lama5267
//        package Vk;


        import com.javastream.vk_api_javastream.utils.Util;
        import com.vk.api.sdk.client.TransportClient;
        import com.vk.api.sdk.client.VkApiClient;
        import com.vk.api.sdk.client.actors.GroupActor;
        import com.vk.api.sdk.exceptions.ApiException;
        import com.vk.api.sdk.exceptions.ClientException;
        import com.vk.api.sdk.httpclient.HttpTransportClient;
        import com.vk.api.sdk.objects.messages.*;
        import com.vk.api.sdk.objects.photos.Photo;
        import com.vk.api.sdk.objects.photos.PhotoUpload;
        import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
        import org.json.JSONObject;

        import java.io.File;
        import java.io.IOException;
        import java.net.URL;
        import java.util.*;

        class App {
        private static GroupActor actor;
        private static VkApiClient vk;
        private static Keyboard keyboard = new Keyboard();

        private static void accept(Message message) {
        if (!message.isOut()) {
        System.out.println(message.toString());

        try {
        if (message.getText().contains("Привет")) {
        sendMessage("Привет я бот игра! Нажми на алфавит или приз", message); }
        else
        if (message.getText().contains("пока")) {
        sendMessage("Удачи!", message);
        }else if (message.getText().contains("А")) { File file = new File("D:\\А.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Б", file, message);}
        else if (message.getText().contains("Б")) { File file = new File("D:\\Б.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву В",file, message);
        }else if (message.getText().contains("В")) { File file = new File("D:\\В.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Г", file, message);}
        else if (message.getText().contains("Г")) { File file = new File("D:\\Г.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Д",file, message);
        }else if (message.getText().contains("Д")) { File file = new File("D:\\Д.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Е", file, message);}
        else if (message.getText().contains("Е")) { File file = new File("D:\\Е.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Ё",file, message);
        }else if (message.getText().contains("Ё")) { File file = new File("D:\\Ё.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ж", file, message);}
        else if (message.getText().contains("Ж")) { File file = new File("D:\\Ж.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву З",file, message);
        }else if (message.getText().contains("З")) { File file = new File("D:\\З.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву И", file, message);}
        else if (message.getText().contains("И")) { File file = new File("D:\\И.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Й",file, message);
        }else if (message.getText().contains("Й")) { File file = new File("D:\\Й.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву К", file, message);}
        else if (message.getText().contains("К")) { File file = new File("D:\\К.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Л",file, message);
        }else if (message.getText().contains("Л")) { File file = new File("D:\\Л.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву М", file, message);}
        else if (message.getText().contains("М")) { File file = new File("D:\\М.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Н",file, message);
        }else if (message.getText().contains("Н")) { File file = new File("D:\\Н.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву О", file, message);}
        else if (message.getText().contains("О")) { File file = new File("D:\\О.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву П",file, message);
        }else if (message.getText().contains("П")) { File file = new File("D:\\П.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Р", file, message);}
        else if (message.getText().contains("Р")) { File file = new File("D:\\Р.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву С",file, message);
        }else if (message.getText().contains("С")) { File file = new File("D:\\С.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Т", file, message);}
        else if (message.getText().contains("Т")) { File file = new File("D:\\Т.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву У",file, message);
        }else if (message.getText().contains("У")) { File file = new File("D:\\У.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ф", file, message);}
        else if (message.getText().contains("Ф")) { File file = new File("D:\\Ф.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Х",file, message);
        }else if (message.getText().contains("Х")) { File file = new File("D:\\Х.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ц", file, message);}
        else if (message.getText().contains("Ц")) { File file = new File("D:\\Ц.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Ч",file, message);
        }else if (message.getText().contains("Ч")) { File file = new File("D:\\Ч.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ш", file, message);}
        else if (message.getText().contains("Ш")) { File file = new File("D:\\Ш.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Щ",file, message);
        }else if (message.getText().contains("Щ")) { File file = new File("D:\\Щ.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ъ", file, message);}
        else if (message.getText().contains("Ъ")) { File file = new File("D:\\Ъ.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Ы",file, message);
        }else if (message.getText().contains("Ы")) { File file = new File("D:\\Ы.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ь", file, message);}
        else if (message.getText().contains("Ь")) { File file = new File("D:\\Ь.jpg");
        sendPhotoMessage( "Молодец! Теперь напиши большую букву Э",file, message);
        }else if (message.getText().contains("Э")) { File file = new File("D:\\Э.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Ю", file, message);}
        else if (message.getText().contains("Ю")) { File file = new File("D:\\Ю.jpg");
        sendPhotoMessage("Молодец! Теперь напиши большую букву Я", file, message);}
        else if (message.getText().contains("2")) { File file = new File("D:\\Пример1.jpg");
        sendPhotoMessage("Молодец! Теперь реши пример 2+2= напиши  ответ цифрой 5 или 4 ", file, message);}
        else if (message.getText().contains("4")) { File file = new File("D:\\Пример2.jpg");
        sendPhotoMessage( "Молодец! Теперь последний примнер 3+3= напигши ответ цифрой 7 или 6",file, message);
        }                else if (message.getText().contains("Я")) { File file = new File("D:\\Я.jpg");
        sendPhotoMessage( "Молодец! 1 уровень пройден Теперь реши пример 1+1= напиши ответ цифрой 2 или 3",file, message);}
        else if (message.getText().contains("6")) { File file = new File("D:\\Пример3.jpg");
        sendPhotoMessage( "Молодец! 2 уровень пройден. Теперь загадка. Ку-ка-ре-ку кричит он звонко,Хлопает крыльями громко-громко,\n" +
        "\nКурочек верный пастух,\n" +
        "\nКак зовут его (п...х).\n" +
        "\n",file, message);}
        else if (message.getText().contains("петух")) { File file = new File("D:\\Загадка1.jpg");
        sendPhotoMessage( "Молодец! Последняя загадка до приза. Ночью он совсем не спит,\n" +
        "\nДом от мышек сторожит,\n" +
        "\n Молоко из миски пьёт,\n" +
        "\nНу конечно это (к.т).\n" +
        "\n",file, message);}
        else if (message.getText().contains("кот")) { File file = new File("D:\\Ура.jpg");
        sendPhotoMessage( "Молодец! Приз твой скажи маме пароль: солнышко и она отдаст тебе приз",file, message);}
        else if (message.getText().contains("солнышко")) { File file = new File("D:\\Солнышко.jpg");
        sendPhotoMessage( "Приз у мамы я просто бот",file, message);}
        else if (message.getText().contains("приз")) { File file = new File("D:\\Приз.jpg");
        sendPhotoMessage( "Чтобы получить приз нужно пройти весь алфавит от А до Я решить три примера и отгадать две загадки",file, message);}
        else if (message.getText().contains("алфавит")) { File file = new File("D:\\Алфавит.jpg");
        sendPhotoMessage( "1 уровень Алфавит нужно писать по одной заглавной букве напиши букву А",file, message);}

        else  sendMessage("Нажми на алфавит или приз и узнай что нужно делать. Если не верно\n" +
        "\nто вспомни последнюю букву которую нажимал. Помни что все буквы заглавные А Б В Г Д ...а слова с маленькой приз кот ", message);


        } catch (ApiException | ClientException | IOException e) {
        e.printStackTrace();
        }
        }
        }
        public static void sendPhotoMessage(String text, File file, Message message) throws ClientException, ApiException, IOException {
        if (message == null) return;

        PhotoUpload uploadServer = vk.photos().getMessagesUploadServer(actor).execute();
        URL uploadUrl = uploadServer.getUploadUrl();

        JSONObject responce = Util.uploadFileToServer(uploadUrl, file);

        int serverJson = responce.getInt("server");
        String photo = responce.getString("photo");
        String hash = responce.getString("hash");

        List<Photo> photoList = vk.photos().saveMessagesPhoto(actor, photo).server(serverJson).hash(hash).execute();

        vk.messages()
        .send(actor)
        .peerId(message.getPeerId())
        .message(text)
        .attachment("photo" + photoList.get(0).getOwnerId() + "_" + photoList.get(0).getId())
        .randomId((int) (Math.random() * 2048))
        .executeAsRaw();
        }
        public static void sendMessage(String text, Message message) throws ClientException {
        if (message == null) return;
        vk.messages()
        .send(actor)
        .peerId(message.getPeerId())
        .message(text)
        .randomId((int) (Math.random() * 2048))
        .keyboard(keyboard)
        .executeAsRaw();
        }
        public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);

        Random random = new Random();


        List<List<KeyboardButton>> allKey = new ArrayList<>();

        List<KeyboardButton> line1 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("алфавит").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("приз").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.NEGATIVE));

        //  List<KeyboardButton> line2 = new ArrayList<>();
        // line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setType(KeyboardButtonActionType.LOCATION)));

        allKey.add(line1);
        // allKey.add(line2);
        keyboard.setButtons(allKey);

        actor = new GroupActor(199598002, "");        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();

        while (true) {
        MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
        List<Message> messages = historyQuery.execute().getMessages().getItems();
        if (!messages.isEmpty()) {

        messages.forEach(App::accept);

        }

        ts = vk.messages().getLongPollServer(actor).execute().getTs();
        Thread.sleep(2000);
        }

        }

        }
*/