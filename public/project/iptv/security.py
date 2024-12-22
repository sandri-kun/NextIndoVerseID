from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import base64

def encrypt_string(data: str, key: str) -> str:
    # Mengubah key menjadi 16 byte (AES hanya mendukung ukuran tertentu, seperti 16, 24, atau 32 byte)
    key = key.ljust(16)[:16].encode('utf-8')

    # Membuat cipher AES
    cipher = Cipher(algorithms.AES(key), modes.ECB(), backend=default_backend())
    encryptor = cipher.encryptor()

    # Padding data agar kelipatan 16 byte
    padded_data = data.ljust((len(data) + 15) // 16 * 16)

    # Enkripsi data
    encrypted_data = encryptor.update(padded_data.encode('utf-8')) + encryptor.finalize()

    # Encode hasil enkripsi ke Base64 agar mudah disimpan atau dikirim
    return base64.b64encode(encrypted_data).decode('utf-8')

def decrypt_string(encrypted_data: str, key: str) -> str:
    # Mengubah key menjadi 16 byte
    key = key.ljust(16)[:16].encode('utf-8')

    # Membuat cipher AES
    cipher = Cipher(algorithms.AES(key), modes.ECB(), backend=default_backend())
    decryptor = cipher.decryptor()

    # Decode data terenkripsi dari Base64
    encrypted_bytes = base64.b64decode(encrypted_data)

    # Dekripsi data
    decrypted_data = decryptor.update(encrypted_bytes) + decryptor.finalize()

    # Menghapus padding data
    return decrypted_data.decode('utf-8').strip()

# Contoh penggunaan
if __name__ == "__main__":
    key = "mysecretkey12345"  # Panjang key disesuaikan menjadi 16 karakter
    data = "Ini adalah data yang akan dienkripsi"

    encrypted = encrypt_string(data, key)
    print("Hasil Enkripsi:", encrypted)

    decrypted = decrypt_string(encrypted, key)
    print("Hasil Dekripsi:", decrypted)
