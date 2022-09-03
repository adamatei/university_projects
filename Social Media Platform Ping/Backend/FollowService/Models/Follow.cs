namespace FollowService.Models
{
    public class Follow
    {
        public virtual int Id { get; set; }
        public virtual string Follower { get; set; }
        public virtual string Followed { get; set; }
        public virtual bool isAccepted { get; set; } 
        public virtual bool isPending { get; set; } 
     
    }
}
