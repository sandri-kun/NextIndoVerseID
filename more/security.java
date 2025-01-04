package more;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class security {

    public static String PUBLIC_KEY = "indoverseidscure";

    public static void main(String[] args) {
        // Variabel untuk setiap bagian data
        String version = "1.2";
        String app_id = "ca-app-pub-3940256099942544~3347511713";
        String banner_id = "ca-app-pub-3940256099942544/9214589741";
        String interstitial_id = "ca-app-pub-3940256099942544/1033173712";
        String native_id = "ca-app-pub-3940256099942544/2247696110";
        String rewarded_id = "ca-app-pub-3940256099942544/5224354917";
        String app_open_id = "ca-app-pub-3940256099942544/9257395921";

        try {
            // Enkripsi setiap variabel
            String encryptedVersion = encryptString(version, PUBLIC_KEY);
            String encryptedAppId = encryptString(app_id, PUBLIC_KEY);
            String encryptedBannerId = encryptString(banner_id, PUBLIC_KEY);
            String encryptedInterstitialId = encryptString(interstitial_id, PUBLIC_KEY);
            String encryptedNativeId = encryptString(native_id, PUBLIC_KEY);
            String encryptedRewardedId = encryptString(rewarded_id, PUBLIC_KEY);
            String encryptedAppOpenId = encryptString(app_open_id, PUBLIC_KEY);

            // Tampilkan hasil enkripsi
            System.out.println("Encrypted Data:");
            System.out.println("Version: " + encryptedVersion);
            System.out.println("App ID: " + encryptedAppId);
            System.out.println("Banner ID: " + encryptedBannerId);
            System.out.println("Interstitial ID: " + encryptedInterstitialId);
            System.out.println("Native ID: " + encryptedNativeId);
            System.out.println("Rewarded ID: " + encryptedRewardedId);
            System.out.println("App Open ID: " + encryptedAppOpenId);

            // Dekripsi kembali untuk verifikasi
            System.out.println("\nDecrypted Data:");
            System.out.println("Version: " + decryptString(encryptedVersion, PUBLIC_KEY));
            System.out.println("App ID: " + decryptString(encryptedAppId, PUBLIC_KEY));
            System.out.println("Banner ID: " + decryptString(encryptedBannerId, PUBLIC_KEY));
            System.out.println("Interstitial ID: " + decryptString(encryptedInterstitialId, PUBLIC_KEY));
            System.out.println("Native ID: " + decryptString(encryptedNativeId, PUBLIC_KEY));
            System.out.println("Rewarded ID: " + decryptString(encryptedRewardedId, PUBLIC_KEY));
            System.out.println("App Open ID: " + decryptString(encryptedAppOpenId, PUBLIC_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptString(String data, String key) throws Exception {
		// Mengubah key string menjadi SecretKey menggunakan AES
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

		// Membuat cipher AES
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

		// Enkripsi data
		byte[] encryptedData = cipher.doFinal(data.getBytes());

		// Encode hasil enkripsi ke Base64 agar mudah disimpan atau dikirim
		return Base64.getEncoder().encodeToString(encryptedData);
	}

	public static String decryptString(String encryptedData, String key) throws Exception {
		// Mengubah key string menjadi SecretKey menggunakan AES
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

		// Membuat cipher AES
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

		// Decode data terenkripsi dari Base64
		byte[] decodedEncryptedData = Base64.getDecoder().decode(encryptedData);

		// Dekripsi data
		byte[] decryptedData = cipher.doFinal(decodedEncryptedData);

		// Mengembalikan data yang didekripsi sebagai string
		return new String(decryptedData);
	}
}