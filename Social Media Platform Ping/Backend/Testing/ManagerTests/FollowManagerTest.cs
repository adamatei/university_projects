using FollowService.DataAccess;
using FollowService.Managers;
using FollowService.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Threading.Tasks;

namespace Testing.ManagerTests
{
    [TestClass]
    public class FollowManagerTest
    {
       
        [TestMethod]
        public void GetFollowByIdTest()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
               mockSet.As<IDbAsyncEnumerable<Follow>>()
                   .Setup(m => m.GetAsyncEnumerator())
                   .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

               mockSet.As<IQueryable<Follow>>()
                   .Setup(m => m.Provider)
                   .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

               mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
               mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
               mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());                      

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            mockContext.Setup(c => c.Follows.FindAsync(It.IsAny<int>())).Returns(new ValueTask<Follow>(follow1));           

            var service = new FollowManager(mockContext.Object);

            //Act       
            Follow follow = service.GetFollowById(1).Result;

            //Assert
            Assert.AreEqual(1, follow.Id);
            Assert.AreEqual("followed1", follow.Followed);
            Assert.AreEqual("follower1", follow.Follower);
            Assert.IsTrue(follow.isAccepted);
            Assert.IsFalse(follow.isPending);
        }


        [TestMethod]
        public void GetFollowedOfUserTest()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            var service = new FollowManager(mockContext.Object);

            //Act       
            List<string> followed = service.GetFollowedOfUser("follower1");

            //Assert
            CollectionAssert.AreEqual(new List<string> { "followed1" }, followed);            
        }


        [TestMethod]
        public void GetFollowerOfUserTest()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            var service = new FollowManager(mockContext.Object);

            //Act       
            List<string> followed = service.GetFollowersOfUser("followed1");

            //Assert
            CollectionAssert.AreEqual(new List<string> { "follower1" }, followed);
        }


        [TestMethod]
        public void DeleteFollowTest_Successful()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);            
            
            var service = new FollowManager(mockContext.Object);

            //Act       
            var result = service.DeleteFollow(1).Result;

            //Assert
            Assert.IsTrue(result);
        }



        [TestMethod]
        public void DeleteFollowTest_Unsuccessful()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);

            var service = new FollowManager(mockContext.Object);

            //Act       
            var result = service.DeleteFollow(8).Result;

            //Assert
            Assert.IsFalse(result);
        }


        [TestMethod]
        public void CreateFollowTest_Successful()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            Follow follow4 = new Follow { Id = 4, Followed = "followed4", Follower = "follower4", isAccepted = true, isPending = false };
            var service = new FollowManager(mockContext.Object);

            //Act       
            Follow result = service.CreateFollow(follow4).Result;

            //Assert
            Assert.AreEqual(follow4.Id, result.Id);
            Assert.AreEqual(follow4.Followed, result.Followed);
            Assert.AreEqual(follow4.Follower, result.Follower);
            Assert.AreEqual(follow4.isAccepted, result.isAccepted);
            Assert.AreEqual(follow4.isPending, result.isPending);
        }


        [TestMethod]
        public void CreateFollowTest_UnSuccessful()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            Follow follow4 = new Follow { Id = 1, Followed = "followed4", Follower = "follower4", isAccepted = true, isPending = false };
            var service = new FollowManager(mockContext.Object);

            //Act       
            Follow result = service.CreateFollow(follow4).Result;

            //Assert
            Assert.IsNull(result);
        }


        [TestMethod]
        public void UpdateFollowTest_Successful()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            Follow actualFollow = new Follow { Id = 3, Followed = "followed4", Follower = "follower4", isAccepted = true, isPending = false };
            var service = new FollowManager(mockContext.Object);

            //Act       
            Follow result = service.UpdateFollow(actualFollow).Result;

            //Assert
            Assert.AreEqual(actualFollow.Id, result.Id);
            Assert.AreEqual(actualFollow.Followed, result.Followed);
            Assert.AreEqual(actualFollow.Follower, result.Follower);
            Assert.AreEqual(actualFollow.isAccepted, result.isAccepted);
            Assert.AreEqual(actualFollow.isPending, result.isPending);
        }


        [TestMethod]
        public void UpdateFollowTest_Unsuccessful()
        {
            //Arrange
            Follow follow1 = new Follow { Id = 1, Followed = "followed1", Follower = "follower1", isAccepted = true, isPending = false };
            Follow follow2 = new Follow { Id = 2, Followed = "followed2", Follower = "follower2", isAccepted = false, isPending = true };
            Follow follow3 = new Follow { Id = 3, Followed = "followed3", Follower = "follower3", isAccepted = false, isPending = false };
            var data = new List<Follow>
            {
                follow1,
                follow2,
                follow3,
            }.AsQueryable();

            var mockSet = new Mock<DbSet<Follow>>();
            mockSet.As<IDbAsyncEnumerable<Follow>>()
                .Setup(m => m.GetAsyncEnumerator())
                .Returns(new TestDbAsyncEnumerator<Follow>(data.GetEnumerator()));

            mockSet.As<IQueryable<Follow>>()
                .Setup(m => m.Provider)
                .Returns(new TestDbAsyncQueryProvider<Follow>(data.Provider));

            mockSet.As<IQueryable<Follow>>().Setup(m => m.Expression).Returns(data.Expression);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.ElementType).Returns(data.ElementType);
            mockSet.As<IQueryable<Follow>>().Setup(m => m.GetEnumerator()).Returns(data.GetEnumerator());

            var mockContext = new Mock<DatabaseContext>();
            mockContext.Setup(c => c.Follows).Returns(mockSet.Object);
            Follow actualFollow = new Follow { Id = 4, Followed = "followed4", Follower = "follower4", isAccepted = true, isPending = false };
            var service = new FollowManager(mockContext.Object);

            //Act       
            Follow result = service.UpdateFollow(actualFollow).Result;

            //Assert
            Assert.IsNull(result);
        }
    }
}
