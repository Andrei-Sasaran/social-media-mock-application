using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesktopClient.DTOs
{
    internal class PostDTO
    {
        public long Id { get; set; }
        public string Title { get; set; }
        public string Content { get; set; }
        public string Status { get; set; }
        public DateTime CreatedOn { get; set; }
        public UserDTO User { get; set; }

        public string DisplayPost => $"{Title} ({Status})";
    }
}
