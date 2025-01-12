using DesktopClient.DTOs;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DesktopClient
{
    public partial class DesktopClient : Form
    {
        public DesktopClient()
        {
            InitializeComponent();
        }

        private ApiClient _apiClient = new ApiClient();
        private List<PostDTO> _currentPosts = new List<PostDTO>();

        private void btnSearch_Click(object sender, EventArgs e)
        {
            string keyword = textKeyword.Text;
            LoadPosts(keyword);
        }

        private async void LoadPosts(string keyword)
        {
            try
            {
                _currentPosts = await _apiClient.GetPostsByKeywordAsync(keyword);
                lstPosts.DataSource = null;
                lstPosts.DataSource = _currentPosts;
                lstPosts.DisplayMember = "DisplayPost";
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error fetching posts: " + ex.Message);
            }
        }

        private async void btnApprove_Click(object sender, EventArgs e)
        {
            if (lstPosts.SelectedItem is PostDTO selectedPost)
            {
                bool success = await _apiClient.ApprovePostAsync(selectedPost.Id);
                if (success)
                {
                    MessageBox.Show("Post approved!");
                    LoadPosts(textKeyword.Text);
                }
                else
                {
                    MessageBox.Show("Error approving post");
                }
            }
        }

        private async void btnRemove_Click(object sender, EventArgs e)
        {
            if (lstPosts.SelectedItem is PostDTO selectedPost)
            {
                bool success = await _apiClient.RemovePostAsync(selectedPost.Id);
                if (success)
                {
                    MessageBox.Show("Post removed!");
                    LoadPosts(textKeyword.Text);
                }
                else
                {
                    MessageBox.Show("Error removing post");
                }
            }
        }

        private List<UserDTO> _allUsers = new List<UserDTO>();

        private async void btnLoadUsers_Click(object sender, EventArgs e)
        {
            try
            {
                _allUsers = await _apiClient.GetAllUsersAsync();
                lstUsers.DataSource = null;
                lstUsers.DataSource = _allUsers;
                lstUsers.DisplayMember = "DisplayName"; 
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error loading users: " + ex.Message);
            }
        }

        private async void btnSendEmail_Click(object sender, EventArgs e)
        {
            var selectedEmails = new List<string>();

            foreach (var selectedObj in lstUsers.SelectedItems)
            {
                if (selectedObj is UserDTO user)
                {
                    selectedEmails.Add(user.Email);
                }
            }

            if (selectedEmails.Count == 0)
            {
                MessageBox.Show("Please select at least one user!");
                return;
            }

            var emailDto = new EmailDTO
            {
                Subject = txtSubject.Text,
                Body = txtBody.Text,
                Recipients = selectedEmails
            };

            bool success = await _apiClient.SendEmailAsync(emailDto);
            if (success)
            {
                MessageBox.Show("Email sent to selected users!");
            }
            else
            {
                MessageBox.Show("Error sending email.");
            }
        }
    }
}
