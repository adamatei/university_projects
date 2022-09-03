using Microsoft.EntityFrameworkCore;
using ProfileService.Models;

namespace ProfileService.DataAccess
{
    public class DatabaseContext:DbContext
    {
        public virtual DbSet<Profile> Profiles { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                  "Data Source = sqlserver; " +
                  "Initial Catalog=ProfileService;" +
                  "User id=sa;" +
                  "Password=authentication123!;");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Profile>(entity =>
            {
                entity.HasKey(e => e.Id);
            });
        }
    }
}
