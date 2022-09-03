using System;

namespace MassTransitModels
{
    public class FollowRequest
    {
        public int Id { get; set; }
        public string Follower { get; set; }
        public string Followed { get; set; }
        public bool isAccepted { get; set; }
        public bool isPending { get; set; }
    }
}
