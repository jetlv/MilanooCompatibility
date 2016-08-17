package com.milanoo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;



/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils{




    /**
     * 
     * @param sa
     * @param str
     * @return
     * @author 吕超
     * @date 2015年10月29日 上午9:27:44 判断字符串数组中是否含有某字符串
     */
    public static boolean ifContains(String[] sa, String str) {

        for (int i = 0; i < sa.length; i++) {
            if (sa[i].equals(str))
                return true;
        }
        return false;
    }

    /**
     * 
     * @param size
     * @return
     * @author 吕超
     * @date 2015年10月29日 上午10:00:05 生产一个随机字符串
     */
    public static String getRandomString(int size) {

        Random r = new Random();

        String scope = "qwertyuiopasdfghjklzxcvbnm123456789";

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < size; i++) {

            result.append(scope.charAt(r.nextInt(scope.length())));
        }

        return result.toString();
    }

    /**
     * 
     * @param exp
     * @return
     * @author 吕超
     * @date 2015年10月29日 上午10:03:52
     * 
     *       根据 ${}的变量表达式取得中间的key
     */
    public static String getVariableKeyFromExp(String exp) {
        return exp.substring(exp.indexOf("{") + 1, exp.indexOf("}"));
    }

    /**
     * 
     * @param str
     *            要处理的字符串
     * @param pToken
     *            prefix token 起始标识符
     * @param eToken
     *            end token 终点标识符
     * @return
     * @author 吕超
     * @date 2015年11月30日 下午3:07:22
     * 
     *       从一段字符串中以开始结束符来截取这个字符串 e.g: cutFromString("I {love} god", "{", "}")
     *       将会返回 {love}
     */
    public static String cutFromString(String str, String pToken, String eToken) {
        int begin = str.indexOf(pToken);
        int end = str.indexOf(eToken) + 1;
        return str.substring(begin, end);
    }

    /**
     * 
     * @param paramArrayOfByte
     * @return
     * @author 吕超
     * @date 2015年12月14日 下午2:31:19
     * 
     * Sha512
     */
    public static byte[] Sha512Sum(byte[] paramArrayOfByte)
    {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-512");
        localMessageDigest.update(paramArrayOfByte);
        paramArrayOfByte = localMessageDigest.digest();
        return paramArrayOfByte;
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
      return null;
    }

    public static byte[] CompressAndEncryptBytesEncodeV2(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
              throws Exception
            {
              byte[] magicCode4 = { -89, -105, 109, 21 };
              Object localObject = compressV2(paramArrayOfByte2);
              paramArrayOfByte2 = MustCryptoRandBytes(16);
              ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
              localByteArrayOutputStream.write((byte[])localObject);
              localByteArrayOutputStream.write(magicCode4);
              localObject = Cipher.getInstance("AES/CTR/NoPadding");
              ((Cipher)localObject).init(1, new SecretKeySpec(paramArrayOfByte1, "AES"), new IvParameterSpec(paramArrayOfByte2));
              paramArrayOfByte1 = ((Cipher)localObject).doFinal(localByteArrayOutputStream.toByteArray());
              localByteArrayOutputStream = new ByteArrayOutputStream();
              localByteArrayOutputStream.write(paramArrayOfByte2);
              localByteArrayOutputStream.write(paramArrayOfByte1);
              return localByteArrayOutputStream.toByteArray();
            }

     private static byte[] compressV2(byte[] paramArrayOfByte)
              throws Exception
            {
              Object localObject = ZlibMustCompress(paramArrayOfByte);
                localObject = new ByteArrayOutputStream();
                ((ByteArrayOutputStream)localObject).write(0);
                ((ByteArrayOutputStream)localObject).write(paramArrayOfByte);
                return ((ByteArrayOutputStream)localObject).toByteArray();
            }

     public static byte[] ZlibMustCompress(byte[] paramArrayOfByte)
        {
          Deflater localDeflater = new Deflater();
          localDeflater.setInput(paramArrayOfByte);
          localDeflater.finish();
          ByteArrayOutputStream b = new ByteArrayOutputStream();
          byte[] arrayOfByte = new byte[' '];
          while (!localDeflater.finished()) {
            b.write(arrayOfByte, 0, localDeflater.deflate(arrayOfByte));
          }
          localDeflater.end();
          return b.toByteArray();
        }

     public static byte[] MustCryptoRandBytes(int paramInt)
        {
          SecureRandom localSecureRandom = new SecureRandom();
          byte[] arrayOfByte = new byte[paramInt];
          localSecureRandom.nextBytes(arrayOfByte);
          return arrayOfByte;
        }

     public static String MarshalToString(Object paramObject)
        {
          return getGson().toJson(paramObject);
        }

     static class Once
        {
          private boolean isInit = false;
          private Object locker = new Object();

          public void Do(Runnable paramRunnable)
          {
            synchronized (this.locker)
            {
              if (this.isInit) {
                return;
              }
              paramRunnable.run();
              this.isInit = true;
              return;
            }
          }
        }
     private static Gson getGson()
        {
          new Once().Do(new Runnable()
          {
            public void run()
            {
              JsonSerializer local1 = new JsonSerializer()
              {
                public JsonElement serialize(Date paramAnonymous2Date, Type paramAnonymous2Type, JsonSerializationContext paramAnonymous2JsonSerializationContext)
                {
                  if (paramAnonymous2Date == null) {
                    return null;
                  }
                  return new JsonPrimitive(FormatGolangDate(paramAnonymous2Date));
                }

                @Override
                public JsonElement serialize(Object src, Type typeOfSrc,
                        JsonSerializationContext context) {
                    // TODO Auto-generated method stub
                    return null;
                }
              };
              JsonDeserializer local2 = new JsonDeserializer()
              {
                public Date deserialize(JsonElement paramAnonymous2JsonElement, Type paramAnonymous2Type, JsonDeserializationContext paramAnonymous2JsonDeserializationContext)
                  throws JsonParseException
                {
                  if (paramAnonymous2JsonElement == null) {
                    return null;
                  }
                  try
                  {
                    Date p = ParseGolangDate(paramAnonymous2JsonElement.getAsString());
                    return p;
                  }
                  catch (Exception e)
                  {
                    throw new JsonParseException(e.getMessage());
                  }
                }
              };
             gson = new GsonBuilder().registerTypeAdapter(Date.class, local1).registerTypeAdapter(Date.class, local2).create();
            }
          });
          return gson;
        }

     public static Date ParseGolangDate(String paramString)
              throws Exception
            {
              Calendar localCalendar = Calendar.getInstance();
              int k = Integer.parseInt(paramString.substring(0, 4));
              int m = Integer.parseInt(paramString.substring(5, 7));
              int n = Integer.parseInt(paramString.substring(8, 10));
              int i1 = Integer.parseInt(paramString.substring(11, 13));
              int i2 = Integer.parseInt(paramString.substring(14, 16));
              int i3 = Integer.parseInt(paramString.substring(17, 19));
              float f = 0.0F;
              int i = 19;
              int j = i;
              int i4;
              if (paramString.charAt(19) == '.')
              {
                do
                {
                  j = i + 1;
                  if (paramString.length() <= j) {
                    throw new Exception("can not parse " + paramString);
                  }
                  i4 = paramString.charAt(j);
                  if (i4 < 48) {
                    break;
                  }
                  i = j;
                } while (i4 <= 57);
                f = Float.parseFloat("0." + paramString.substring(20, j));
              }
              localCalendar.set(14, (int)(f * 1000.0D));
              i4 = paramString.charAt(j);
              if (i4 == 90) {
                localCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
              }
              for (;;)
              {
                localCalendar.set(k, m - 1, n, i1, i2, i3);
                return localCalendar.getTime();
//                j = Integer.parseInt(paramString.substring(j + 1, j + 3)) * 3600 * 1000 + Integer.parseInt(paramString.substring(j + 4, j + 6)) * 60 * 1000;
//                i = j;
//                if (i4 == 45) {
//                  i = -j;
//                }
//                localCalendar.setTimeZone(new SimpleTimeZone(i, ""));
              }
            }

     public static Gson gson;

     public static String FormatGolangDate(Date paramDate)
        {
          Calendar localCalendar = Calendar.getInstance();
          localCalendar.setTime(paramDate);
          StringBuilder sb = new StringBuilder();
          formatYear(localCalendar, sb);
          sb.append('-');
          formatMonth(localCalendar, sb);
          sb.append('-');
          formatDays(localCalendar, sb);
          sb.append('T');
          formatHours(localCalendar, sb);
          sb.append(':');
          formatMinutes(localCalendar, sb);
          sb.append(':');
          formatSeconds(localCalendar, sb);
          formatTimeZone(localCalendar, sb);
          return paramDate.toString();
        }

     private static void formatDays(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          formatTwoDigits(paramCalendar.get(5), paramStringBuilder);
        }

        private static void formatHours(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          formatTwoDigits(paramCalendar.get(11), paramStringBuilder);
        }

        private static void formatMinutes(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          formatTwoDigits(paramCalendar.get(12), paramStringBuilder);
        }

        private static void formatMonth(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          formatTwoDigits(paramCalendar.get(2) + 1, paramStringBuilder);
        }

        private static void formatSeconds(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          formatTwoDigits(paramCalendar.get(13), paramStringBuilder);
          if (paramCalendar.isSet(14))
          {
            int i = paramCalendar.get(14);
            String str;
            if (i != 0)
            {
              for (str = Integer.toString(i); str.length() < 3; str = '0' + str) {}
              paramStringBuilder.append('.');
              paramStringBuilder.append(paramCalendar);
            }
          }
        }

        private static void formatTimeZone(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          TimeZone localTimeZone = paramCalendar.getTimeZone();
          if (localTimeZone == null) {
            return;
          }
          int i = localTimeZone.getOffset(paramCalendar.getTime().getTime());
          if (i == 0)
          {
            paramStringBuilder.append('Z');
            return;
          }
          if (i >= 0) {
            paramStringBuilder.append('+');
          }
          for (;;)
          {
            i /= 60000;
            formatTwoDigits(i / 60, paramStringBuilder);
            paramStringBuilder.append(':');
            formatTwoDigits(i % 60, paramStringBuilder);
            paramStringBuilder.append('-');
            i *= -1;
            return;
          }
        }

        private static void formatTwoDigits(int paramInt, StringBuilder paramStringBuilder)
        {
          if (paramInt < 10) {
            paramStringBuilder.append('0');
          }
          paramStringBuilder.append(paramInt);
        }

        private static void formatYear(Calendar paramCalendar, StringBuilder paramStringBuilder)
        {
          int i = paramCalendar.get(1);
          String str = "";
          if (i <= 0) {
              str = Integer.toString(1 - i);
          }
          while (str.length() < 4)
          {
              str = '0' + str;
            continue;
          }
          Object localObject = paramCalendar;
          if (i <= 0) {
            localObject = '-' + str;
          }
          paramStringBuilder.append((String)localObject);
        }

//        public static void main(String[] args) throws Exception {
//            byte[] b = new byte[]{ 0x14,(byte) 0x7E,(byte)  0xC1,(byte) 0x6F,(byte) 0x11,(byte)  0xE4,(byte)  0xD9,(byte) 0xF1,(byte) 0x47,(byte) 0x2B,(byte) 0x29,(byte) 0x01,(byte) 0x8C,(byte) 0x27,(byte) 0x4D,(byte) 0xB8,(byte) 0x6D,(byte) 0xEA,(byte) 0x78,(byte) 0x81,(byte) 0xF9,(byte) 0x88,(byte) 0x76,(byte) 0x80,(byte) 0x91,(byte) 0x36,(byte) 0xA2,(byte) 0xAE,(byte) 0x5B,(byte) 0x6F,(byte) 0x1F,(byte) 0x40,(byte) 0xE2,(byte) 0x2F,(byte) 0x25,(byte) 0xD4,(byte) 0xA9,(byte) 0x80,(byte) 0xEE,(byte) 0x37,(byte) 0x87,(byte) 0x5E,(byte) 0x60,(byte) 0xD6,(byte) 0xF1,(byte) 0x00,(byte) 0x7E,(byte) 0xE7,(byte) 0x18,(byte) 0xF2,(byte) 0x4D,(byte) 0xB2,(byte) 0xC7,(byte) 0x30,(byte) 0x02,(byte) 0xF1,(byte) 0x4E,(byte) 0x7F,(byte) 0x35,(byte) 0x45,(byte) 0x48,(byte) 0xEA,(byte) 0x46,(byte) 0xC8,(byte) 0x90,(byte) 0x53,(byte) 0x88,(byte) 0xC8,(byte) 0x8F,(byte) 0xCE,(byte) 0xF4,(byte) 0xD9,(byte) 0xF6,(byte) 0xA9,(byte) 0x01,(byte) 0x89,(byte) 0x6C,(byte) 0xF9,(byte) 0x37,(byte) 0x4B,(byte) 0xF5,(byte) 0x27,(byte) 0xFA,(byte) 0xCC,(byte) 0xBD,(byte) 0x5A,(byte) 0xCE,(byte) 0xFB,(byte) 0x37,(byte) 0x74,(byte) 0x79,(byte) 0x75,(byte) 0x1B,(byte) 0x72,(byte) 0x3C,(byte) 0x4F,(byte) 0x0F,(byte) 0xC2,(byte) 0xC7,(byte) 0x5F,(byte) 0x5B,(byte) 0x7F,(byte) 0x88,(byte) 0xC7,(byte) 0xA9,(byte) 0x87,(byte) 0x2F,(byte) 0x74,(byte) 0xB8,(byte) 0x1C,(byte) 0x0D,(byte) 0x92,(byte) 0xCC,(byte) 0x18,(byte) 0x2F,(byte) 0x8A,(byte) 0x1C,(byte) 0xFC,(byte) 0xC5,(byte) 0x8D,(byte) 0x7A,(byte) 0x87,(byte) 0x7A,(byte) 0x3E,(byte) 0xA1,(byte) 0xE0,(byte) 0x6B,(byte) 0x04,(byte) 0xB7,(byte) 0x53,(byte) 0x0B,(byte) 0x6C,(byte) 0x32,(byte) 0x6E,(byte) 0x75,(byte) 0x77,(byte) 0xAB,(byte) 0xA9,(byte) 0x87,(byte) 0x6D,(byte) 0x00,(byte) 0x38,(byte) 0x06,(byte) 0x4A,(byte) 0x52,(byte) 0xD5,(byte) 0xE1,(byte) 0xC4,(byte) 0xCD,(byte) 0x64,(byte) 0x92,(byte) 0x0B,(byte) 0xD2,(byte) 0x2B,(byte) 0x0C,(byte) 0x7C,(byte) 0x9E,(byte) 0x85,(byte) 0x4A,(byte) 0xEC,(byte) 0x8B,(byte) 0x49,(byte) 0x77,(byte) 0x6D,(byte) 0x40,(byte) 0x02,(byte) 0x2B,(byte) 0x66,(byte) 0x37,(byte) 0x6B,(byte) 0x00,(byte) 0x6A,(byte) 0x22,(byte) 0x88,(byte) 0x9A,(byte) 0xE7,(byte) 0xE6,(byte) 0x79,(byte) 0xE8,(byte) 0x49,(byte) 0x7C,(byte) 0xE0,(byte) 0xF4,(byte) 0xF3,(byte) 0x2C,(byte) 0xCC,(byte) 0xFD,(byte) 0xCC,(byte) 0x55,(byte) 0x16,(byte) 0x35,(byte) 0x75 };
//            byte[] result = CompressAndEncryptBytesDecodeV2(Global.getPsk(), b);
//            
//            System.out.println(new String(result, "UTF-8"));
//        }

        public static byte[] CompressAndEncryptBytesDecodeV2(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
                  throws Exception
                {
                  if (paramArrayOfByte2.length < 21) {
                    throw new Exception("[kmgCrypto.CompressAndEncryptBytesDecode] input data too small");
                  }
                  byte[] arrayOfByte =  Arrays.copyOfRange(paramArrayOfByte2, 0, 16);
                  paramArrayOfByte2 =  Arrays.copyOfRange(paramArrayOfByte2, 16, paramArrayOfByte2.length);
                  Cipher localCipher = Cipher.getInstance("AES/CTR/NoPadding");
                  localCipher.init(2, new SecretKeySpec(paramArrayOfByte1, "AES"), new IvParameterSpec(arrayOfByte));
                  paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
                  paramArrayOfByte2 =  Arrays.copyOfRange(paramArrayOfByte1, 0, paramArrayOfByte1.length - 4);
                  byte[] magicCode4 = { -89, -105, 109, 21 };
                  if (!Arrays.equals(magicCode4, Arrays.copyOfRange(paramArrayOfByte1, paramArrayOfByte1.length - 4, paramArrayOfByte1.length))) {
                    throw new Exception("[kmgCrypto.CompressAndEncryptBytesDecode] magicCode not match");
                  }
                  return uncompressV2(paramArrayOfByte2);
                }

        private static byte[] uncompressV2(byte[] paramArrayOfByte)
                  throws Exception
                {
                  if (paramArrayOfByte.length == 0) {
                    throw new Exception("[uncopressV2] len(inData)==0");
                  }
                  if (paramArrayOfByte[0] == 0) {
                    return Arrays.copyOfRange(paramArrayOfByte, 1, paramArrayOfByte.length);
                  }
                  return ZlibUnCompress(Arrays.copyOfRange(paramArrayOfByte, 1, paramArrayOfByte.length));
                }

public static byte[] ZlibUnCompress(byte[] paramArrayOfByte)
          throws Exception
        {
          Inflater localInflater = new Inflater();
          localInflater.setInput(paramArrayOfByte);
          ByteArrayOutputStream b = new ByteArrayOutputStream();
          byte[] arrayOfByte = new byte[' '];
          for (;;)
          {
            int i;
            if (!localInflater.finished())
            {
              i = localInflater.inflate(arrayOfByte);
              if (i != 0) {}
            }
            else
            {
              localInflater.end();
              return b.toByteArray();
            }
            b.write(arrayOfByte, 0, i);
          }
        }

public static <T> T UnmarshalFromString(String paramString, Class<T> paramClass)
        throws JsonSyntaxException
      {
        return (T)getGson().fromJson(paramString, paramClass);
      }

}
