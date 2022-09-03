using System;
using System.Text;
using System.Security.Cryptography;

namespace AuthenticationService
{
    public class EncodingAlgorithm
    {
        public string EncodeCustom(string Data)
        {
            try
            {
                byte[] oByte = new byte[Data.Length];
                oByte = System.Text.Encoding.UTF8.GetBytes(Data);
                string EncodedData = "25" + Convert.ToBase64String(oByte);
                return EncodedData;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public string DecodeCustom(string Data)
        {
            try
            {
                System.Text.UTF8Encoding oUTF8Encoding = new System.Text.UTF8Encoding();
                System.Text.Decoder oDecoder = oUTF8Encoding.GetDecoder();
                byte[] oByte = Convert.FromBase64String(Data);
                int CharCount = oDecoder.GetCharCount(oByte, 0, oByte.Length);
                char[] DecodedChar = new char[CharCount];
                oDecoder.GetChars(oByte, 0, oByte.Length, DecodedChar, 0);
                string DecodedData = new String(DecodedChar);
                return DecodedData;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        //This method is used to convert the plain text to Encrypted/Un-Readable Text format.
        public string EncryptTripleDES(string PlainText)
        {
            
            byte[] toEncryptedArray = UTF8Encoding.UTF8.GetBytes(PlainText);
            MD5CryptoServiceProvider objMD5CryptoService = new MD5CryptoServiceProvider();
            //var securityKey = Environment.GetEnvironmentVariable("SECURITY_KEY");
            var securityKey = "ExtraSecurityKey_1234";
            byte[] securityKeyArray = objMD5CryptoService.ComputeHash(UTF8Encoding.UTF8.GetBytes(securityKey));        
            objMD5CryptoService.Clear();
            var objTripleDESCryptoService = new TripleDESCryptoServiceProvider();           
            objTripleDESCryptoService.Key = securityKeyArray;         
            objTripleDESCryptoService.Mode = CipherMode.ECB;           
            objTripleDESCryptoService.Padding = PaddingMode.PKCS7;
            var objCrytpoTransform = objTripleDESCryptoService.CreateEncryptor();           
            byte[] resultArray = objCrytpoTransform.TransformFinalBlock(toEncryptedArray, 0, toEncryptedArray.Length);
            objTripleDESCryptoService.Clear();
            return Convert.ToBase64String(resultArray, 0, resultArray.Length);
        }

        //This method is used to convert the Encrypted/Un-Readable Text back to readable  format.
        public string DecryptTripleDES(string CipherText)
        {
            byte[] toEncryptArray = Convert.FromBase64String(CipherText);
            MD5CryptoServiceProvider objMD5CryptoService = new MD5CryptoServiceProvider();
            var securityKey = Environment.GetEnvironmentVariable("SECURITY_KEY");
            byte[] securityKeyArray = objMD5CryptoService.ComputeHash(UTF8Encoding.UTF8.GetBytes(securityKey));
            objMD5CryptoService.Clear();
            var objTripleDESCryptoService = new TripleDESCryptoServiceProvider();           
            objTripleDESCryptoService.Key = securityKeyArray;   
            objTripleDESCryptoService.Mode = CipherMode.ECB;           
            objTripleDESCryptoService.Padding = PaddingMode.PKCS7;
            var objCrytpoTransform = objTripleDESCryptoService.CreateDecryptor();           
            byte[] resultArray = objCrytpoTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);
            objTripleDESCryptoService.Clear();
            return UTF8Encoding.UTF8.GetString(resultArray);
        }
    }
}
