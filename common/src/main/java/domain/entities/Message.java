package domain.entities;

import java.io.*;

public class Message {

    public static final int PORT = 1234;
    public static final String HOST = "localhost";

    private String header;
    private String body;

    public Message(String header, String body){
        this.header = header;
         this.body = body;
    }

    public Message() {

    }

    public String getHeader(){ return this.header; }
    public String getBody(){ return this.body;}
    public void setHeader(String op){ this.header = op;}
    public void setBody(String body) { this.body = body;}

    @Override
    public String toString(){
        return "Message{" +
                "header='" + header + "'" +
                ", body='" + body + "'}";
    }

    public void readFrom(InputStream is) throws IOException {
        var br = new BufferedReader(new InputStreamReader(is));
        String bufferMessage = "";
        do {
            bufferMessage += br.readLine();
            bufferMessage += System.lineSeparator();
        } while (br.ready());
        bufferMessage = bufferMessage.substring(0, bufferMessage.length() - System.lineSeparator().length());
        String[] inputParsed = bufferMessage.split(System.lineSeparator(), 2);

        header = inputParsed[0];
        if (inputParsed.length > 1) {
            body = inputParsed[1];
        }
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write((header + System.lineSeparator() + body + System.lineSeparator()).getBytes());
    }
}
