using ProfileService.Models;
using System;
using System.Security.Cryptography;
using System.Text;

namespace ProfileService.Services

{
    public class EncrytionLogic
    {
        public EncrytionLogic() { }

        public virtual Profile DecryptProfile(Profile profile)
        {
            profile.Intro = DecryptTripleDES(profile.Intro);
            profile.Job = DecryptTripleDES(profile.Job);
            profile.Education = DecryptTripleDES(profile.Education);
            profile.LivingCity = DecryptTripleDES(profile.LivingCity);
            profile.LivingCountry = DecryptTripleDES(profile.LivingCountry);
            profile.OriginCityy = DecryptTripleDES(profile.OriginCityy);
            profile.OriginCountry = DecryptTripleDES(profile.OriginCountry);

            return profile;
        }
        public virtual string EncryptTripleDES(string PlainText)
        {

            byte[] toEncryptedArray = UTF8Encoding.UTF8.GetBytes(PlainText);
            MD5CryptoServiceProvider objMD5CryptoService = new MD5CryptoServiceProvider();
            //var securityKey = Environment.GetEnvironmentVariable("profileKey");
            var securityKey = "profileKey";
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
            //var securityKey = Environment.GetEnvironmentVariable("profileKey");
            var securityKey = "profileKey";
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
