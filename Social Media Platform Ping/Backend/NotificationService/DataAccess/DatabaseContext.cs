using Microsoft.EntityFrameworkCore;
using NotificationService.Models;

namespace NotificationService.DataAccess
{
    public class DatabaseContext: DbContext
    {
        public virtual DbSet<Notification> Notifications { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                  "Data Source =127.0.0.1; " +
                  "Initial Catalog=NotificationService;" +
                  "User id=sa;" +
                  "Password=authentication123!;");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Notification>(entity =>
            {
                entity.HasKey(e => e.Id);
            });         
        }
    }
}
