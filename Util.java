package local;

import android.util.Base64;

import org.bson.BsonBinaryWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by jeppe on 11/16/16.
 */

public class Util {

    final protected static char[] hexArray = "0123456789abcdef".toCharArray();



    //public static native String bsonToString(byte[] bson_bytes);

    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static Codec<Document> DOCUMENT_CODEC = new DocumentCodec();

    public static byte[] jsonToByteArray(String json) {
        final Document document = Document.parse(json);
        BasicOutputBuffer outputBuffer = new BasicOutputBuffer();
        BsonBinaryWriter writer = new BsonBinaryWriter(outputBuffer);
        DOCUMENT_CODEC.encode(writer, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        return outputBuffer.toByteArray();
    }

    public static String byteArrayToZipString(byte[] input) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(input);
        gos.close();
        byte[] compressed = os.toByteArray();
        os.close();
        return Base64.encodeToString(compressed, Base64.DEFAULT);
    }

    public static byte[] stringToByteArrayZip(String input) throws IOException{
            byte[] DocumentCompressed = Base64.decode(input, Base64.DEFAULT);
            ByteArrayInputStream is = new ByteArrayInputStream(DocumentCompressed);
            GZIPInputStream gis = new GZIPInputStream(is);
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len;
            while ((len = gis.read(buffer)) > 0) {
                out.write(buffer, 0, len);

            }
            is.close();
            gis.close();
            out.close();
            return out.toByteArray();
    }


}
