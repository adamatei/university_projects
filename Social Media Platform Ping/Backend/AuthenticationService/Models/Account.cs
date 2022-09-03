namespace AuthenticationService.Models
{
    public class Account
    {
        public int Id { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
        public bool isBlocked { get; set; }
        public string Role { get; set; }
    }
}
