import { useState, useEffect } from "react";
import { Table, Button, Space, Image, Popconfirm, message, Modal } from "antd";
import {
  EyeOutlined,
  EditOutlined,
  DeleteOutlined,
  PlusOutlined,
} from "@ant-design/icons";
import { fetchDataMovie } from "../service/api.service";
import CreateMovieForm from "../component/movies/create.movies";

const Movies = () => {
  const [movies, setMovies] = useState([]);
  const [pagination, setPagination] = useState({
    current: 1,
    pageSize: 5,
    total: 0,
  });
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    fetchMovies(pagination.current, pagination.pageSize);
  }, [pagination.current, pagination.pageSize]);

  const fetchMovies = async (page, pageSize) => {
    try {
      const response = await fetchDataMovie(page, pageSize);
      setMovies(response.content);
      setPagination((prev) => ({
        ...prev,
        total: response.totalElements,
      }));
    } catch (error) {
      message.error("Lỗi khi tải dữ liệu phim");
    }
  };

  const handleCreate = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const columns = [
    { title: "ID", dataIndex: "id", key: "id", width: 60 },
    {
      title: "Image",
      dataIndex: "thumbnailUrl",
      key: "thumbnailUrl",
      render: (url) => <Image width={50} src={url} />,
    },
    { title: "Title", dataIndex: "title", key: "title" },
    { title: "Genre", dataIndex: "genre", key: "genre" },
    { title: "Rating", dataIndex: "rating", key: "rating", width: 100 },
    { title: "Price ($)", dataIndex: "price", key: "price", width: 100 },
    { title: "Created At", dataIndex: "createdAt", key: "createdAt" },
    {
      title: "Action",
      key: "action",
      width: 200,
      render: (_, record) => (
        <Space>
          <Button icon={<EyeOutlined />} />
          <Button icon={<EditOutlined />} />
          <Popconfirm
            title="Bạn có chắc chắn muốn xóa phim này?"
            okText="Yes"
            cancelText="No"
          >
            <Button danger icon={<DeleteOutlined />} />
          </Popconfirm>
        </Space>
      ),
    },
  ];

  return (
    <>
      <h2 style={{ textAlign: "center" }}>🎬 Quản lý Movies</h2>
      <Button
        type="primary"
        icon={<PlusOutlined />}
        onClick={handleCreate}
        style={{ marginBottom: 16 }}
      >
        Tạo mới
      </Button>
      <Table
        dataSource={movies}
        columns={columns}
        pagination={{
          current: pagination.current,
          pageSize: pagination.pageSize,
          total: pagination.total,
          onChange: (page, pageSize) =>
            setPagination({ current: page, pageSize }),
        }}
        rowKey="id"
      />

      {/* Modal Tạo Mới */}
      <Modal
        title="Tạo phim mới"
        open={isModalOpen}
        onCancel={handleCloseModal}
        footer={null}
      >
        {/* Truyền fetchMovies xuống để cập nhật danh sách sau khi thêm */}
        <CreateMovieForm
          onSuccess={() => {
            fetchMovies(pagination.current, pagination.pageSize);
            handleCloseModal();
          }}
        />
      </Modal>
    </>
  );
};

export default Movies;
