using AuthenticationService.Models;
using Microsoft.EntityFrameworkCore;

namespace AuthenticationService.DataAccess
{
    public class DatabaseContext : DbContext
    {
        public DbSet<Account> Accounts { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                  "Data Source=127.0.0.1; " +
                  "Initial Catalog=AuthenticationService;" +
                  "User id=sa;" +
                  "Password=authentication123!;");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Account>(entity =>
            {
                entity.HasKey(e => e.Id);
            });
        }
    }
}
