using FollowService.Models;
using Microsoft.EntityFrameworkCore;

namespace FollowService.DataAccess
{
    public class DatabaseContext:DbContext
    {
        public virtual DbSet<Follow> Follows { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                  "Data Source = sqlserver; " +
                  "Initial Catalog=FollowService;" +
                  "User id=sa;" +
                  "Password=authentication123!;");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Follow>(entity =>
            {
                entity.HasKey(e => e.Id);
            });
        }
    }
}
