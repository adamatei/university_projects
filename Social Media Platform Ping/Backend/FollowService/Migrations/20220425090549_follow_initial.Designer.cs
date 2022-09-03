﻿// <auto-generated />
using FollowService.DataAccess;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace FollowService.Migrations
{
    [DbContext(typeof(DatabaseContext))]
    [Migration("20220425090549_follow_initial")]
    partial class follow_initial
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.5")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("FollowService.Models.Follow", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Followed")
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Follower")
                        .HasColumnType("nvarchar(max)");

                    b.Property<bool>("isAccepted")
                        .HasColumnType("bit");

                    b.Property<bool>("isPending")
                        .HasColumnType("bit");

                    b.HasKey("Id");

                    b.ToTable("Follows");
                });
#pragma warning restore 612, 618
        }
    }
}
