using AuthenticationService.DataAccess;
using AuthenticationService.Models;
using MassTransit;
using MassTransitModels;
using System;
using System.Linq;
using System.Threading.Tasks;

namespace AuthenticationService.Consumers
{
    public class BlockUpdateConsumer : IConsumer<BlockUpdate>
    {
        private readonly IJWTAuthenticationManager manager;
       
        public BlockUpdateConsumer(IJWTAuthenticationManager manager)
        {
            this.manager = manager;          
        }
        public async Task Consume(ConsumeContext<BlockUpdate> context)
        {
            var msg = context.Message;
           
            try
            {
                await manager.UpdateAccountAccess(msg.Username, msg.isBlocked, msg.Role);
           
                await Console.Out.WriteLineAsync(msg.Username + " is blocked " + msg.isBlocked);
            }
            catch(Exception e)
            {
                await Console.Out.WriteLineAsync(e.Message);
            }
        }
    }
}
