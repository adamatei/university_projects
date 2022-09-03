using AuthenticationService.Models;
using System.Threading.Tasks;

namespace AuthenticationService
{
    public interface IJWTAuthenticationManager
    {
        string Authenticate(string username, string password);
        Task<string> Register(string username, string password);
        Task<Account> UpdateAccountAccess(string username, bool isBlocked, string role);

        Task<bool> DeleteAccount(string username);
    }
}
