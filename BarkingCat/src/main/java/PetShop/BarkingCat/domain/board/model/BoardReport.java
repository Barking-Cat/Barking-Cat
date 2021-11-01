package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.model.Base;
import PetShop.BarkingCat.common.base.model.constants.ReportCategory;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class BoardReport extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private Long reporterId;

    @Enumerated(EnumType.STRING)
    private ReportCategory reportCategory;

    private String detail;

    public BoardReport() {
    }

    @Builder
    public BoardReport(Long id, Board board, Long reporterId, ReportCategory reportCategory, String detail) {
        this.id = id;
        this.board = board;
        this.reporterId = reporterId;
        this.reportCategory = reportCategory;
        this.detail = detail;
    }

    public BoardReport board(Board board) {
        this.board = board;
        return this;
    }

    public BoardReport reporterId(Long reporterId) {
        this.reporterId = reporterId;
        return this;
    }
}
