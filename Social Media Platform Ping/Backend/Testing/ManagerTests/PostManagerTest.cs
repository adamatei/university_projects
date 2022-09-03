using Microsoft.EntityFrameworkCore;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using PostService.DataAccess;
using PostService.Managers;
using PostService.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Testing.ManagerTests
{
    [TestClass]
    public class PostManagerTest
    {
        [TestMethod]
        public void GetNotificationByIdTest_Successful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            mockContext.Setup(c => c.Posts.FindAsync(It.IsAny<int>())).Returns(new ValueTask<Post>(post1));

            var service = new PostManager(mockContext.Object);

            //Act       
            Post result = service.GetPostById(1);

            //Assert
            Assert.AreEqual(post1.Id, result.Id);
            Assert.AreEqual(post1.CreatedBy, result.CreatedBy);
            Assert.AreEqual(post1.CreatedOn, result.CreatedOn);
            Assert.AreEqual(post1.Text, result.Text);
            CollectionAssert.AreEqual(post1.Tags.ToArray(), result.Tags.ToArray());
        }


        [TestMethod]
        public void GetNotificationByIdTest_Unsuccessful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            mockContext.Setup(c => c.Posts.FindAsync(It.IsAny<int>())).Returns(null);

            var service = new PostManager(mockContext.Object);

            //Act       
            Post result = service.GetPostById(9);

            //Assert
            Assert.IsNull(result);
        }


        [TestMethod]
        public void GetPostsByUsernameTest_Successful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username1", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 1, CreatedBy = "username2", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 1, CreatedBy = "username3", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
        
            var service = new PostManager(mockContext.Object);

            //Act       
            List<Post> result = service.GetPostsByUsername("username1");

            //Assert           
            CollectionAssert.AreEqual(new List<Post> { post1 }.ToArray(), result.ToArray());
        }


        [TestMethod]
        public void GetPostsByUsernameTest_Unsuccessful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            mockContext.Setup(c => c.Posts.FindAsync(It.IsAny<int>())).Returns(null);

            var service = new PostManager(mockContext.Object);

            //Act       
            List<Post> result = service.GetPostsByUsername("username100");

            //Assert
            CollectionAssert.AreEqual(new List<Post>().ToArray(), result.ToArray());
        }


        [TestMethod]
        public void DeletePostTest_Successful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username1", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username2", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username3", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            
            var service = new PostManager(mockContext.Object);

            //Act       
            bool result = service.DeletePost(1).Result;

            //Assert
            Assert.IsTrue(result);
        }


        [TestMethod]
        public void DeletePostTest_Unsuccessful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username1", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username2", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username3", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
           
            var service = new PostManager(mockContext.Object);

            //Act       
            bool result = service.DeletePost(9).Result;

            //Assert
            Assert.IsFalse(result);
        }


        [TestMethod]
        public void CreatePostTest_Successful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            Post newPost = new Post { Id = 4, CreatedBy = "username4", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var service = new PostManager(mockContext.Object);

            //Act       
            Post result = service.CreatePost(newPost).Result;

            //Assert
            Assert.AreEqual(newPost.Id, result.Id);
            Assert.AreEqual(newPost.CreatedBy, result.CreatedBy);
            Assert.AreEqual(newPost.CreatedOn, result.CreatedOn);
            Assert.AreEqual(newPost.Text, result.Text);
            CollectionAssert.AreEqual(newPost.Tags.ToArray(), result.Tags.ToArray());
        }


        [TestMethod]
        public void UpdatePostTest_Successful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            Post updatePost = new Post { Id = 3, CreatedBy = "username3", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() { new Tag { Id = 1, Content = "fun", Type = "hashtag" } } };

            var service = new PostManager(mockContext.Object);

            //Act       
            Post result = service.UpdatePost(updatePost).Result;

            //Assert           
            CollectionAssert.AreEqual(updatePost.Tags.ToArray(), result.Tags.ToArray());
        }


        [TestMethod]
        public void UpdatePostTest_Unsuccessful()
        {
            //Arrange
            Post post1 = new Post { Id = 1, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post2 = new Post { Id = 2, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };
            Post post3 = new Post { Id = 3, CreatedBy = "username", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var data = new List<Post>
            {
                post1,
                post2,
                post3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Post>>();
            mockSet.As<IDbAsyncEnumerable<Post>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Post>(data.GetEnumerator()));

            mockSet.As<IQueryable<Post>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Post>(data.Provider));

            mockSet.As<IQueryable<Post>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Post>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Post>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Posts).Returns(mockSet.Object);
            Post updatePost = new Post { Id = 4, CreatedBy = "username4", CreatedOn = DateTime.Now, Text = "This is a post!", Tags = new List<Tag>() };

            var service = new PostManager(mockContext.Object);

            //Act       
            Post result = service.UpdatePost(updatePost).Result;

            //Assert
            Assert.IsNull(result);
        }
    }
}
