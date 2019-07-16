package com.inbm.inbmstarter.inbm;

import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by inbm on 2017. 3. 24..
 */

public class _web {

    static boolean isIran = false;

    public static String get(String url) throws IOException {
        String html;
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        html = response.body().string();

        return html;
    }

    public static String post(String url, String json) {
        if (isIran) {
            InputStream is;
            String result = "";
            try {
                URL urlCon = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();

//            jsonObject.accumulate("twitter", person.getTwitter());/* convert JSONObject to JSON to String*/
                httpCon.setDoOutput(true);/* InputStream으로 서버로 부터 응답을 받겠다는 옵션.*/
                httpCon.setDoInput(true);
                OutputStream os = httpCon.getOutputStream();
                os.write(json.getBytes(StandardCharsets.UTF_8));
                os.flush();/* receive response as inputStream*/
                try {
                    is = httpCon.getInputStream();/* convert inputstream to string*/
                    if (is != null) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        try {
                            while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        result = sb.toString();
                    } else result = "Did not work!";
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    httpCon.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ignored) {
            }
            return result;
        }
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();

        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("post error", e.getMessage());
        }
        return html;
    }

    //TODO REVIEW
    public static String delete(String url, String json) {
        if (isIran) {
//            InputStream is;
//            String result = "";
            try {
                URL urlCon = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();
                httpCon.setRequestMethod("DELETE");
//            jsonObject.accumulate("twitter", person.getTwitter());/* convert JSONObject to JSON to String*/

                Charset charset = Charset.forName("UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), charset));

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).delete(body).build();

        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("delete error", e.getMessage());
        }
        return html;
    }



    public static String postWithHeaders(String url, String json, _header_... headers) {

        if (isIran) {
            InputStream is;
            String result = "";
            try {
                URL urlCon = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();

//            jsonObject.accumulate("twitter", person.getTwitter());/* convert JSONObject to JSON to String*/
                for (_header_ header : headers) {
                    httpCon.setRequestProperty(header.key, header.value);
                }
                httpCon.setDoOutput(true);/* InputStream으로 서버로 부터 응답을 받겠다는 옵션.*/
                httpCon.setDoInput(true);
                OutputStream os = httpCon.getOutputStream();
                os.write(json.getBytes(StandardCharsets.UTF_8));
                os.flush();/* receive response as inputStream*/
                try {
                    is = httpCon.getInputStream();/* convert inputstream to string*/
                    if (is != null) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        try {
                            while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        result = sb.toString();
                    } else result = "Did not work!";
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    httpCon.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ignored) {
            }
            return result;
        }

//        int proxyPort = 8888;
//        String proxyHost = "180.210.205.199";
//        final String usernsme = "username";
//        final String password = "password";

//        Authenticator proxyAuthenticator = new Authenticator() {
//            @Nullable
//            @Override
//            public Request authenticate(Route route, Response response) throws IOException {
//                String credential = Credentials.basic(usernsme, password);
//                return response
//                        .request()
//                        .newBuilder()
//                        .header("Proxy-Authorization", credential)
//                        .build();
//            }
//        };
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
//                .proxyAuthenticator(proxyAuthenticator)
                .build();


        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder();


        //Request request = new Request.Builder();//.url(url).post(body).build();

        for (_header_ header : headers) {
            builder.addHeader(header.key, header.value);
        }
        Request request = builder.url(url).post(body).build();
        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("post error", e.getMessage());
        }
        return html;
    }

    public static String getWithHeaders(String url, _header_... headers) {
        if (isIran) {
//            InputStream is;
//            String result = "";
            try {
                URL urlCon = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();
                httpCon.setRequestMethod("GET");
//            jsonObject.accumulate("twitter", person.getTwitter());/* convert JSONObject to JSON to String*/
                for (_header_ header : headers) {
                    httpCon.setRequestProperty(header.key, header.value);
                }

                Charset charset = Charset.forName("UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), charset));

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request.Builder builder = new Request.Builder();


        //Request request = new Request.Builder();//.url(url).post(body).build();

        for (_header_ header : headers) {
            builder.addHeader(header.key, header.value);
        }
        Request request = builder.url(url).build();

        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("get error", e.getMessage());
        }
        return html;
    }

    public static String deleteWithHeaders(String url, String json, _header_... headers) {
        if (isIran) {
//            InputStream is;
//            String result = "";
            try {
                URL urlCon = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) urlCon.openConnection();
                httpCon.setRequestMethod("DELETE");
//            jsonObject.accumulate("twitter", person.getTwitter());/* convert JSONObject to JSON to String*/
                for (_header_ header : headers) {
                    httpCon.setRequestProperty(header.key, header.value);
                }
                httpCon.setDoOutput(true);/* InputStream으로 서버로 부터 응답을 받겠다는 옵션.*/
                httpCon.setDoInput(true);
                OutputStream os = httpCon.getOutputStream();
                os.write(json.getBytes(StandardCharsets.UTF_8));
                os.flush();/* receive response as inputStream*/

                Charset charset = Charset.forName("UTF-8");
                BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), charset));

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();


        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder();


        //Request request = new Request.Builder();//.url(url).post(body).build();

        for (_header_ header : headers) {
            builder.addHeader(header.key, header.value);
        }
        Request request = builder.url(url).delete(body).build();


        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("detele error", e.getMessage());
        }
        return html;
    }

    public static String upload(String url, File file) throws IOException {
        //headers[0] = new _web._header_("Content-Type","multipart/form-data");
        MediaType mediaType = MediaType.parse("image/jpeg");

        RequestBody formBody =
                new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //.addFormDataPart("file", file.getName(),RequestBody.create(mediaType
                        //,readFile(file))).build();
                .addFormDataPart("file", file.getName(),
                        RequestBody.create( mediaType, file))
                        .build();
               // .addFormDataPart("other_field", "other_field_value")
        OkHttpClient client = new OkHttpClient();
//        new OkHttpClient.Builder()
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .writeTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("Authorization", _shared.getBearerToken())
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void uploadAsync(String url, File file , final AbsWeb.OnJsonLoadListener listener ) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("image/jpeg");

        RequestBody formBody =
                new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        //.addFormDataPart("file", file.getName(),RequestBody.create(mediaType
                        //,readFile(file))).build();
                        .addFormDataPart("file", file.getName(),
                                RequestBody.create( mediaType, file))
                        .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("Authorization", _shared.getBearerToken())
                .build();
        //Response response = client.newCall(request).execute();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String result = response.body().string();
                    try {
                        listener.onJsonLoad(result);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }




    public static class _header_ {
        public _header_(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }


}
