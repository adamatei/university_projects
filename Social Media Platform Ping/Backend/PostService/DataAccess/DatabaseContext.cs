using Microsoft.EntityFrameworkCore;
using PostService.Models;

namespace PostService.DataAccess
{
    public class DatabaseContext: DbContext
    {
        public virtual DbSet<Post> Posts { get; set; }
        public virtual DbSet<Tag> Tags { get; set; }
        //public DbSet<PostTag> PostTags { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                  "Data Source = postservice.database.windows.net; " +
                  "Initial Catalog=postservice;" +
                  "User id=sa_dbo;" +
                  "Password=authentication123!;");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Post>(entity =>
            {
                entity.HasKey(e => e.Id);
            });
            modelBuilder.Entity<Tag>(entity =>
            {
                entity.HasKey(e => e.Id);
            });          
            //modelBuilder.Entity<PostTag>().HasKey(i => new { i.PostId, i.TagId });
            //modelBuilder.Entity<Post>()
            //.HasMany(p => p.Tags)
            //.WithMany(p => p.Posts)
            //.UsingEntity<PostTag>(
            //    j => j
            //        .HasOne(pt => pt.Tag)
            //        .WithMany(t => t.PostTags)
            //        .HasForeignKey(pt => pt.TagId),
            //    j => j
            //        .HasOne(pt => pt.Post)
            //        .WithMany(p => p.PostTags)
            //        .HasForeignKey(pt => pt.PostId),
            //    j => j.HasKey(t => new { t.PostId, t.TagId }));

            /*modelBuilder.Entity<Post>()
           .HasMany(p => p.Tags)
           .WithMany(p => p.Posts)
           .UsingEntity(j => j.ToTable("PostTags")); */

        }
    }
}
