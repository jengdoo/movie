import React, { useState } from "react";
import { Layout, Menu, Button, Modal, Form, Input, message } from "antd";
import { Link } from "react-router-dom";
import {
  DashboardOutlined,
  VideoCameraOutlined,
  UserOutlined,
  DollarOutlined,
  TagOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  PlusOutlined,
  EditOutlined,
  DeleteOutlined,
} from "@ant-design/icons";

const { Sider } = Layout;
const { SubMenu } = Menu;

const Sidebar = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [movies, setMovies] = useState([
    { id: 1, name: "Danh sach" },
    { id: 2, name: "Spider-Man" },
  ]);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [form] = Form.useForm();

  return (
    <Sider
      collapsible
      collapsed={collapsed}
      onCollapse={setCollapsed}
      theme="dark"
    >
      {/* Logo */}
      <div
        style={{
          textAlign: "center",
          padding: "10px",
          color: "white",
          fontSize: "18px",
        }}
      >
        {collapsed ? "🎬" : "Admin Panel"}
      </div>

      {/* Menu */}
      <Menu theme="dark" mode="inline" defaultSelectedKeys={["dashboard"]}>
        <Menu.Item key="dashboard" icon={<DashboardOutlined />}>
          <Link to="/admin/movies">Movies</Link>
        </Menu.Item>

        {/* Dropdown Movies */}
        <SubMenu key="movies" icon={<VideoCameraOutlined />} title="Promotion">
          {movies.map((movie) => (
            <Menu.Item key={movie.id}>{movie.name}</Menu.Item>
          ))}
        </SubMenu>

        <Menu.Item key="users" icon={<UserOutlined />}>
          <Link to="/admin/users">Users</Link>
        </Menu.Item>
        <Menu.Item key="payments" icon={<DollarOutlined />}>
          <Link to="/admin/payments">Payments</Link>
        </Menu.Item>
        <Menu.Item key="vouchers" icon={<TagOutlined />}>
          <Link to="/admin/vouchers">Vouchers</Link>
        </Menu.Item>
      </Menu>
    </Sider>
  );
};

export default Sidebar;
