using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesktopClient.DTOs
{
    internal class UserDTO
    {
        public long Id{  get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string DisplayName => $"{Name} ({Email})";

    }
}
