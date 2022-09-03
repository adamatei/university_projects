using AuthenticationService.DataAccess;
using AuthenticationService.Models;
using Microsoft.Extensions.Logging;
using Microsoft.IdentityModel.Tokens;
using Serilog;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;

namespace AuthenticationService
{
    public class JWTAuthenticationManager : IJWTAuthenticationManager
    {       
        private readonly string key;      
        private readonly EncodingAlgorithm algorithm = new EncodingAlgorithm();
        private readonly DatabaseContext db = new DatabaseContext();

        public JWTAuthenticationManager(string key)
        {
            this.key = key;           
        }
        public string Authenticate(string username, string password)
        {
           string encoded_password = algorithm.EncryptTripleDES(password);
           if (!db.Accounts.Any(u => u.Username == username && u.Password == encoded_password))
           {
                Log.Debug($"{DateTime.Now}: Account with username {username} does not exist");
                return null;
           }
           if (db.Accounts.Any(u => u.Username == username && u.Password == encoded_password && u.isBlocked == true))
           {
                Log.Debug($"{DateTime.Now}: Account with username {username} is blocked");
                return null;
           }
            Account account = db.Accounts.FirstOrDefault(a => a.Username == username);
            var tokenHandler = new JwtSecurityTokenHandler();
            var tokenKey = Encoding.ASCII.GetBytes("This is my test Key");
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new Claim[]
                {
                    new Claim(ClaimTypes.Name, username),
                    new Claim("Role", account.Role)
                }),
                Expires = DateTime.UtcNow.AddHours(1),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(tokenKey),
                SecurityAlgorithms.HmacSha256Signature)
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            return tokenHandler.WriteToken(token);
        }

        public async Task<bool> DeleteAccount(string username)
        {
            if (db.Accounts.Any(u => u.Username == username))
            {
                foreach (var account in db.Accounts.ToList())
                {
                    if(account.Username == username)
                    {
                        db.Accounts.Remove(account);
                        await db.SaveChangesAsync();
                        Log.Debug($"{DateTime.Now}: Account with username {username} was successfully deleted");
                        return true;
                    }
                }
            }
            Log.Debug($"{DateTime.Now}: Account with username {username} could not be deleted: NOT FOUND");
            return false;
        }

        public async Task<string> Register(string username, string password)
        {
            if (!db.Accounts.Any(u => u.Username == username))
            {
                string encoded_password = algorithm.EncryptTripleDES(password);
                Account account = new Account { Username = username, Password = encoded_password, isBlocked = false, Role = "user"};
                await db.Accounts.AddAsync(account);
                Console.WriteLine(db.Accounts.ToList().ToString());
                await db.SaveChangesAsync();
                Log.Debug($"{DateTime.Now}: Account with username {username} was successfully created");
                return username;
            }
            Log.Debug($"{DateTime.Now}: Account with username {username} could not be created: ALREADY EXISTS");
            return null;
        }

        public async Task<Account> UpdateAccountAccess(string username, bool isBlocked, string role)
        {
            foreach (Account a in db.Accounts.ToList())
            {
                if(a.Username == username)
                {
                    a.isBlocked = isBlocked;
                    a.Role = role;
                    db.Update(a);
                    await db.SaveChangesAsync();
                    Log.Debug($"{DateTime.Now}: Account with username {username} was successfully updated");
                    return a;
                }
            }
            Log.Debug($"{DateTime.Now}: Account with username {username} could not be updated updated: NOT FOUND");
            return null;
        }
    }
}
