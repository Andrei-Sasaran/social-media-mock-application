using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesktopClient.DTOs
{
    internal class EmailDTO
    {
        public List<string> Recipients { get; set; } = new List<string>();
        public string Subject { get; set; }
        public string Body { get; set; }
       
    }
}
