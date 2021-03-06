package pl.touk.poker.android.network;

import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class ServerClient {

    private static final String API_URL = "http://192.168.60.189:8080/poker";
    public static final int TIMEOUT = 120000;
    public static String APP_VERSION = "undefined";

    private ServerApi grailsApi;

    public ServerClient() {

        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            okHttpClient.setSslSocketFactory(trustAllHostsSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        okHttpClient.setHostnameVerifier(DO_NOT_VERIFY);
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.MILLISECONDS);

        final OkClient okClient = new OkClient(okHttpClient) {

            @Override
            protected HttpURLConnection openConnection(Request request) throws IOException {
                HttpURLConnection connection = super.openConnection(request);
                connection.setReadTimeout(TIMEOUT);
                connection.setConnectTimeout(TIMEOUT);
                return connection;
            }

            @Override
            public Response execute(Request request) throws IOException {
                Response response = null;
                long start = System.nanoTime();
                try {

                    response = super.execute(request);
                }
                catch (IOException e) {
                    long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
                    throw e;
                }
                return response;
            }

        };

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addHeader("Connection","Keep-Alive");
                requestFacade.addHeader("Accept","application/json");
                requestFacade.addQueryParam("appVersion","Android" + APP_VERSION);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setServer(API_URL)
                .setClient(okClient)
                .build();


        grailsApi = restAdapter.create(ServerApi.class);
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    private SSLSocketFactory trustAllHostsSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }
        };

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection
                .setDefaultSSLSocketFactory(sc.getSocketFactory());
        return sc.getSocketFactory();
    }

    public ServerApi getApi() {
       return grailsApi;
    }

}
