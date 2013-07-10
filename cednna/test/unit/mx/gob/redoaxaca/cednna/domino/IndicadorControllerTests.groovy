package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(IndicadorController)
@Mock(Indicador)
class IndicadorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/indicador/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.indicadorInstanceList.size() == 0
        assert model.indicadorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.indicadorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.indicadorInstance != null
        assert view == '/indicador/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/indicador/show/1'
        assert controller.flash.message != null
        assert Indicador.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/indicador/list'

        populateValidParams(params)
        def indicador = new Indicador(params)

        assert indicador.save() != null

        params.id = indicador.id

        def model = controller.show()

        assert model.indicadorInstance == indicador
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/indicador/list'

        populateValidParams(params)
        def indicador = new Indicador(params)

        assert indicador.save() != null

        params.id = indicador.id

        def model = controller.edit()

        assert model.indicadorInstance == indicador
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/indicador/list'

        response.reset()

        populateValidParams(params)
        def indicador = new Indicador(params)

        assert indicador.save() != null

        // test invalid parameters in update
        params.id = indicador.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/indicador/edit"
        assert model.indicadorInstance != null

        indicador.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/indicador/show/$indicador.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        indicador.clearErrors()

        populateValidParams(params)
        params.id = indicador.id
        params.version = -1
        controller.update()

        assert view == "/indicador/edit"
        assert model.indicadorInstance != null
        assert model.indicadorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/indicador/list'

        response.reset()

        populateValidParams(params)
        def indicador = new Indicador(params)

        assert indicador.save() != null
        assert Indicador.count() == 1

        params.id = indicador.id

        controller.delete()

        assert Indicador.count() == 0
        assert Indicador.get(indicador.id) == null
        assert response.redirectedUrl == '/indicador/list'
    }
}
